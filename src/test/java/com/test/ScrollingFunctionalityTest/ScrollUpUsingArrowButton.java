package com.test.ScrollingFunctionalityTest;

import com.Pages.HomePage;
import com.test.BaseTest;
import com.utility.Report.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScrollUpUsingArrowButton extends BaseTest {

    private HomePage homePage;
    private static final Logger logger = LoggerUtil.getLogger(ScrollUpUsingArrowButton.class);

    @BeforeMethod
    private void initializeObject() {
        homePage = new HomePage(driver);
    }

    @Test(description = "Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    public void scrollUpUsingArrowButton() throws InterruptedException {
        homePage.verifyHomePage();
        homePage.scrollToBottom();
        String footerText = homePage.verifyFooterSubscriptionText();
        if (footerText.equals("SUBSCRIPTION")) {
        } else {
            logger.info("Failed to scroll to the bottom. Footer text: {}", footerText);
        }
        Assert.assertEquals(homePage.verifyFooterSubscriptionText(), "SUBSCRIPTION");
        homePage.clickOnScrollUpButton();
        String homePageText = homePage.homePageText();
        if (homePageText.equals("Full-Fledged practice website for Automation Engineers")) {
        } else {
            logger.info("Failed to scroll up. Home page text: {}", homePageText);
        }
        Assert.assertEquals(homePage.homePageText(), "Full-Fledged practice website for Automation Engineers");
    }
}
