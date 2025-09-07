package com.test.ProductsTest;

import com.test.BaseTest;
import com.Pages.HomePage;
import com.Pages.ProductPages.ProductDetailsPage;
import com.Pages.ProductPages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestProductList extends BaseTest {

  private HomePage homePage;
  private ProductsPage productsPage;
  private ProductDetailsPage productDetailsPage;
  private int productNumber =1;

  @BeforeMethod
  public void initializeObject(){
    homePage = new HomePage(driver);
    productsPage = new ProductsPage(driver);
    productDetailsPage = new ProductDetailsPage(driver);
  }

  @Test(description = "Checks number of available product and click on product to check product details")
  public void verifyAnyProductDetailsByProductNumber() throws InterruptedException {
    homePage.getPageTitle();
    homePage.verifyHomePage();
    homePage.clickOnMenuButton("Products");
    String actualHeader = productsPage.verifyProductPage();
    Assert.assertEquals(actualHeader,"ALL PRODUCTS","Product page not displayed");
    int count = productsPage.getAvailableProductsCount();
    System.out.println("**************** Product Count ******* "+count);
    productsPage.clickToViewSpecificProduct(productNumber);
  }
}
