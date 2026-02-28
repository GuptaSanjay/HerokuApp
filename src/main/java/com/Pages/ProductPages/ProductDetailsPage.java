package com.Pages.ProductPages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends InitializeElements {

  private SeleniumWrappers wrappers;

  @FindBy(id = "name")
  private WebElement nameInput;

  @FindBy(id = "email")
  private WebElement emailInput;

  @FindBy(id = "review")
  private WebElement reviewInput;

  @FindBy(id = "button-review")
  private WebElement submitReviewButton;

  @FindBy(xpath = "//span[contains(text(),'Thank you for your review.')]")
  private WebElement reviewSuccessMessage;

  public ProductDetailsPage(WebDriver driver) {
    super(driver);
    wrappers = new SeleniumWrappers(driver);
  }

  public void addProductReview(String name, String email, String review) throws InterruptedException {
    wrappers.enterValue(nameInput, name);
    wrappers.enterValue(emailInput, email);
    wrappers.enterValue(reviewInput, review);
    wrappers.click(submitReviewButton);
    Thread.sleep(1000);
  }

  public boolean isReviewSuccessDisplayed() {
    try {
      wrappers.waitForElementVisibility(reviewSuccessMessage);
      return reviewSuccessMessage.isDisplayed();
    } catch (org.openqa.selenium.TimeoutException e) {
      // Log the exception and return false
      System.out.println("Timeout waiting for review success message: " + e.getMessage());
      return false;
    }
  }
}
