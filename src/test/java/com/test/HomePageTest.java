package com.test;

import com.BaseTest;
import com.Pages.HomePage;
import com.Pages.LoginSignUpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeClass
    public void driverSetup(){
        this.homePage = new HomePage(driver);

    }

    @Test
    public void testHomePage(){
        String title;
        title = homePage.getPageTitle();
        System.out.println("Home page title is "+title);
    }

    @Test
    public void getPageHeading(){
        String actual = "Website for automation practice";
        String expected = homePage.verifyPageHeading();
        Assert.assertEquals(expected, actual,"Home page is not correct");
    }

    @Test
    public void verifyHomePageOption() throws InterruptedException {
        System.out.println("Avalaibel options are *****"+homePage.getHeaderOption().size());
        if(!homePage.verifyHomePage()){
            homePage.clickOnButtonFromHomePage("Home");
        }
    }

}
