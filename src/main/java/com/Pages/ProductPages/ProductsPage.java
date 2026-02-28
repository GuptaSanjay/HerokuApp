package com.Pages.ProductPages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends InitializeElements {

  private SeleniumWrappers wrappers;

  public ProductsPage(WebDriver driver){
    super(driver);
    wrappers = new SeleniumWrappers(driver);
  }

  @FindBy(xpath = "//div[@class='features_items']/h2")
  private WebElement productSectionHeader;

  @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
  private List<WebElement> productList;

  @FindBy(css = "input#search_product" )
  private WebElement search_input;

  @FindBy(xpath = "//div[@class=\"features_items\"]/div[@class=\"col-sm-4\"]")
  private List<WebElement> search_results;

  @FindBy(css = "button#submit_search")
  private WebElement search_button;

//  @FindBy(xpath = "//div[contains(@class, 'features_items')]//div[contains(@class, 'col-sm-4')][1]//div[contains(@class, 'choose')]//a")
//  private WebElement viewProduct;

  public int getAvailableProductsCount(){
    return productList.size();
  }

  public void clickToViewSpecificProduct(int number) throws InterruptedException {
    WebElement viewProduct = driver.findElement(By.xpath("//div[contains(@class, 'features_items')]//div[contains(@class, 'col-sm-4')]["+number+"]//div[contains(@class, 'choose')]//a"));
    wrappers.scrollToElement(viewProduct);
    wrappers.click(viewProduct);
    wrappers.handleAdvertisement();
    Thread.sleep(500);
    new ProductDetailsPage(driver);
  }

  public void clickToViewProductByName(String productName) throws InterruptedException {
    WebElement productLink = driver.findElement(By.xpath("//a[contains(text(),'" + productName + "') and contains(@href,'/product_details/')][1]"));
    wrappers.scrollToElement(productLink);
    wrappers.click(productLink);
    wrappers.handleAdvertisement();
    Thread.sleep(500);
    new ProductDetailsPage(driver);
  }

  public String verifyProductPage(){
    return wrappers.getText(productSectionHeader);
  }

  public void searchProduct(String productName) throws InterruptedException {
    wrappers.enterValue(search_input,productName);
    wrappers.click(search_button);
  }

  public int searchedProductsCount() throws InterruptedException {
    return search_results.size();
  }

  public Boolean validateAllSearchProductTitle(String matchedWithProductName) throws InterruptedException {
    int productCount = search_results.size();
    boolean productFound = true;
    for(int i = 1; i <= productCount; i++){
      WebElement product= driver.findElement(By.xpath("//div[@class=\"features_items\"]/div[@class=\"col-sm-4\"]["+i+"]/descendant::p[1]"));
      String productTitle = wrappers.getText(product);
      if(!productTitle.toLowerCase().contains(matchedWithProductName.toLowerCase())){
       return  false;
      }
    }
    return productFound;
  }
}
