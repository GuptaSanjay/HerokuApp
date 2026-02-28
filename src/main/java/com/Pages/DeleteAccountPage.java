package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import com.utility.Report.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAccountPage extends InitializeElements {

    private SeleniumWrappers wrappers;
    private static final Logger logger = LoggerUtil.getLogger(DeleteAccountPage.class);

    public DeleteAccountPage(WebDriver driver){
        super(driver);
        this.wrappers = new SeleniumWrappers(driver);
        logger.info("DeleteAccountPage initialized with driver: {}", driver);
    }

    @FindBy(xpath = "//h2[@data-qa=\"account-deleted\"]//b")
    private WebElement deleteAccountText;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    public String getDeleteAccountConfirmation() throws InterruptedException {
      wrappers.waitForElementVisibility(deleteAccountText);
      String confirmation = deleteAccountText.getText();
      logger.info("Account deletion confirmation text: {}", confirmation);
      return confirmation;
    }

    public void continueButtonClick() {
        logger.info("Clicking continue button after account deletion");
        continueButton.click();
        new HomePage(driver);
    }
}
