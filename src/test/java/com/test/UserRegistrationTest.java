package com.test;

import com.BaseTest;
import com.Pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Map;

import static java.util.Map.entry;


public class UserRegistrationTest extends BaseTest {

    private LoginSignUpPage loginSignUpPage;
    private HomePage homePage;
    private CreateAccountPage createAccountPage;
    private AccountCreatedPage accountCreatedPage;
    private LoggedInHomePage loggedInHomePage;
    private DeleteAccountPage deleteAccountPage;

    Map<String, String> addressInfo = Map.ofEntries(
            entry("Address","D-Block, 1100, J J Colony"),
            entry("country","India"),
            entry("city","Bawana"),
            entry("state","Delhi"),
            entry("zipcode","110039"),
            entry("mobile","7299695507"),
            entry("firstName","sanjay"),
            entry("lastName","gupta"),
            entry("day","28"),
            entry("month","02"),
            entry("year","1998"),
            entry("password","Test1234")
            );


    @BeforeClass
    public void driverSetUp(){
        loginSignUpPage = new LoginSignUpPage(driver);
        homePage = new HomePage(driver);
        createAccountPage = new CreateAccountPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        loggedInHomePage = new LoggedInHomePage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
    }

    @Test
    public void registerUser() throws InterruptedException {
        homePage.getPageTitle();
        homePage.verifyHomePage();
        homePage.clickOnButtonFromHomePage("Signup / Login");
        loginSignUpPage.fillFormForSignUp("sanjay","sanjay.cloudtech@gmail.com");
        loginSignUpPage.clickOnSignUp();
        createAccountPage.enterUserDetails(addressInfo);
        createAccountPage.clickOnCreateAccount();
        String text = accountCreatedPage.getAccountCreatedText();
        Assert.assertEquals(text,"ACCOUNT CREATED!");
        accountCreatedPage.continueToVerifyLoggedInUserPage();
        String user = loggedInHomePage.getLoggedInUserName();
        Assert.assertEquals(user,"sanjay");
        loggedInHomePage.clickOnDeleteAccount();
        String deleteText = deleteAccountPage.getDeleteAccountConfirmation();
        Assert.assertEquals(deleteText,"ACCOUNT DELETED!");
        deleteAccountPage.continueButtonClick();
    }

}
