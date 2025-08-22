package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAccountPage extends InitializeElements {

    public DeleteAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[@data-qa=\"account-deleted\"]//b")
    private WebElement deleteAccountText;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    public String getDeleteAccountConfirmation() throws InterruptedException {
      SeleniumWrappers.shortWait();
      return deleteAccountText.getText();
    }

    public HomePage continueButtonClick() {
        continueButton.click();
        return new HomePage(driver);
    }
}
