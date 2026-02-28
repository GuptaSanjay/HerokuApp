package com.test.ProductsTest;

import com.Pages.HomePage;
import com.Pages.ProductPages.ProductsPage;
import com.Pages.ProductPages.ProductDetailsPage;
import com.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddProductReviewTest extends BaseTest {
    private HomePage homePage;
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;

    @BeforeMethod
    public void initializeObject() {
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @Test(description = "Test case 21: Add Review on product")
    public void addReviewOnProduct() throws InterruptedException {
        // Step 1: Navigate to Products page
        homePage.goToProductsPage();

        // Step 2: Click on any product to view details
        productsPage.clickToViewSpecificProduct(1); // Click first product

        // Step 3: Fill review form and submit
        String name = "TestUser";
        String email = "testuser@example.com";
        String review = "This is a test review for automation.";
        productDetailsPage.addProductReview(name, email, review);

        // Step 4: Verify success message
        Assert.assertTrue(productDetailsPage.isReviewSuccessDisplayed(), "Review success message not displayed!");
    }
}
