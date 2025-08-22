package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInHomePage extends InitializeElements {

    public LoggedInHomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()=\" Logged in as \"]//b")
    private WebElement loggedInUser;

    @FindBy(xpath = "//div[@class=\"shop-menu pull-right\"]//ul//li[5]/a")
    private WebElement deleteAccount;

    @FindBy(xpath = "//a[@href=\"/logout\"]")
    private WebElement logOut;


    public String  getLoggedInUserName(){
        return loggedInUser.getText();
    }

    public DeleteAccountPage clickOnDeleteAccount() throws InterruptedException {
        deleteAccount.click();
        SeleniumWrappers.shortWait();
        return new DeleteAccountPage(driver);
    }

    public void logOut() throws InterruptedException {
      logOut.click();
      SeleniumWrappers.shortWait();
    }



}
