package com.Pages.ProductPages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends InitializeElements {

  private SeleniumWrappers wrappers;

  public ProductsPage(WebDriver driver){
    super(driver);
    wrappers = new SeleniumWrappers(driver);
  }

  @FindBy(xpath = "//div[@class='features_items']/h2")
  private WebElement productSectionHeader;

  @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
  private List<WebElement> productList;

//  @FindBy(xpath = "//div[contains(@class, 'features_items')]//div[contains(@class, 'col-sm-4')][1]//div[contains(@class, 'choose')]//a")
//  private WebElement viewProduct;

  public int getAvailableProductsCount(){
    return productList.size();
  }

  public void clickToViewSpecificProduct(int number) throws InterruptedException {
    WebElement viewProduct = driver.findElement(By.xpath("//div[contains(@class, 'features_items')]//div[contains(@class, 'col-sm-4')]["+number+"]//div[contains(@class, 'choose')]//a"));
    wrappers.scrollToElement(viewProduct);
    wrappers.click(viewProduct);
    Thread.sleep(500);
    new ProductDetailsPage(driver);
  }

  public String verifyProductPage(){
    return wrappers.getText(productSectionHeader);
  }
}
