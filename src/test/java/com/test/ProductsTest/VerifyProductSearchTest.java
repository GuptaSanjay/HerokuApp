package com.test.ProductsTest;

import com.Pages.HomePage;
import com.Pages.ProductPages.ProductsPage;
import com.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyProductSearchTest extends BaseTest {
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void initializeObject() {
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Parameters("searchKeyWord")
    @Test(description = "Verify all the products related to search are displayed")
    public void verifySearchedProductsCount(@Optional("White") String searchInput) throws InterruptedException {
        homePage.verifyHomePage();
        homePage.clickOnMenuButton("Products");
        productsPage.verifyProductPage();
        productsPage.searchProduct(searchInput);
        productsPage.getAvailableProductsCount();
        Assert.assertTrue(productsPage.validateAllSearchProductTitle(searchInput));
    }

}
