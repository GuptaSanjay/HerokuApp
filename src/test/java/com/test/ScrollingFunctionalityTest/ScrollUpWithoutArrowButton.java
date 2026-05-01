package com.test.ScrollingFunctionalityTest;

import com.Pages.HomePage;
import com.test.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScrollUpWithoutArrowButton extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void initializeObject() {
        homePage = new HomePage(driver);
    }

    @Test(description = "Test Case 26: Verify Scroll Up without using 'Arrow' button and Scroll Down functionality")
    public void scrollUpWithoutArrowButton() throws InterruptedException {
        homePage.verifyHomePage();
        homePage.scrollToBottom();
        String footerText = homePage.verifyFooterSubscriptionText();
        if (footerText.equals("SUBSCRIPTION")) {
        } else {
            System.out.println("Failed to scroll to the bottom. Footer text: " + footerText);
        }
        homePage.scrollUpWithoutArrowButton();
        String homePageText = homePage.homePageText();
        if (homePageText.equals("Full-Fledged practice website for Automation Engineers")) {
        } else {
            System.out.println("Failed to scroll up. Home page text: " + homePageText);
        }
    }
}
