package com.test;

import com.BaseTest;
import com.Pages.HomePage;
import com.Pages.LoginSignUpPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginSignUpTest extends BaseTest {

    private LoginSignUpPage loginSignUpPage;
    private HomePage homePage;


    @BeforeClass
    public void driverSetup(){
        loginSignUpPage = new LoginSignUpPage(driver);
        homePage = new HomePage(driver);
    }


    @Test
    public void login() throws InterruptedException{
        homePage.clickOnButtonFromHomePage("Signup / Login");
//        loginSignUpPage.enterUserNameForLogin("Sanjay.gupta");
//        loginSignUpPage.enterPasswordForLogin("Sanjay123");
    }

}
