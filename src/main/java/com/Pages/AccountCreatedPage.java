package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends InitializeElements {

  private SeleniumWrappers wrappers;

    public AccountCreatedPage(WebDriver driver){
        super(driver);
        this.wrappers = new SeleniumWrappers(driver);
    }

    @FindBy(xpath = "//h2[@data-qa=\"account-created\"]//b")
    private WebElement accountCreatedText;

    @FindBy(xpath = "//a[@data-qa=\"continue-button\"]")
    private WebElement continueButton;

    public String getAccountCreatedText() throws InterruptedException {
        wrappers.waitForElementVisibility(accountCreatedText);
        //seleniumWrappers.acknowledgeAlert();
        return accountCreatedText.getText();
    }

    public void continueToVerifyLoggedInUserPage() throws InterruptedException {
        wrappers.scrollToElement(continueButton);
        continueButton.click();
        wrappers.shortWait();
      new LoggedInHomePage(driver);
    }
}
