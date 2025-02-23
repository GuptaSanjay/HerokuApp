package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends InitializeElements {

    public AccountCreatedPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[@data-qa=\"account-created\"]//b")
    private WebElement accountCreatedText;

    @FindBy(xpath = "//a[@data-qa=\"continue-button\"]")
    private WebElement continueButton;

    public String getAccountCreatedText() throws InterruptedException {
        SeleniumWrappers.mediumWait();
        //seleniumWrappers.acknowledgeAlert();
        return accountCreatedText.getText();
    }

    public LoggedInHomePage  continueToVerifyLoggedInUserPage() throws InterruptedException {
        SeleniumWrappers.scrollToElement(continueButton);
        continueButton.click();
        SeleniumWrappers.shortWait();
        return new LoggedInHomePage(driver);
    }
}
