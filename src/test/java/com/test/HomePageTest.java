package com.test;

import com.utility.DriverSetUp;
import com.utility.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends DriverSetUp {

    private HomePage homePage;

    @BeforeClass
    public void driverSetup(){
        homePage = new HomePage(driver);
    }

    @Test
    public void testHomePage(){
        String title;
        title = homePage.getPageTitle();
        System.out.println("Home page title is "+title);
    }

    @Test
    public void getPageHeading(){
        String actual = "Available Examples";
        String expected = homePage.verifyPageheading();
        Assert.assertEquals(expected, actual,"Home page is not correct");
    }

}
