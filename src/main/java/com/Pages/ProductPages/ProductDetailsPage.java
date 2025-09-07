package com.Pages.ProductPages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends InitializeElements {

  private SeleniumWrappers wrappers;

  public ProductDetailsPage(WebDriver driver) {
    super(driver);
    wrappers = new SeleniumWrappers(driver);
  }
}
