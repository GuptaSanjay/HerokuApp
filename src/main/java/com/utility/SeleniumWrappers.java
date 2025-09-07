package com.utility;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumWrappers {

    protected WebDriver driver;
    protected int shortWait = 15;
    protected int mediumWait = 25;
    protected WebDriverWait webDriverWait;

    public SeleniumWrappers(WebDriver driver){
        this.driver =driver;
    }

    private void waitForPageTOLoad(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("return document.readyState").toString().equals("complete");
    }

     public void shortWait() throws IllegalArgumentException, InterruptedException {
        Thread.sleep(Duration.ofSeconds(shortWait));
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void click(WebElement element){
        element.click();
    }

    public String getText(WebElement element){
        waitForElementVisibility(element);
        return element.getText();
    }

    public String getAttributeValue(WebElement element, String attribute){
        waitForElementVisibility(element);
        return element.getDomAttribute(attribute);
    }

    public List<String> getListValue(List<WebElement> elements){
        List<String> list = new ArrayList<>();
        for(WebElement element :elements){
            list.add(element.getText());
        }
        return list;
    }

    public void waitForElementVisibility(WebElement element){
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(mediumWait)); // Updated to use Duration
        webDriverWait.until(ExpectedConditions.visibilityOf(element)); // Wait until element is visible
    }

    public  void waitShortToClickable(WebElement element){
        webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(mediumWait));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickOnDynamicButton(String homePageButton){
        WebElement element = driver.findElement(By.xpath(".//a[text()=' "+homePageButton+"']"));
        clickWhenElementClickable(element);
    }

    public void clickWhenElementClickable(WebElement element){
        waitShortToClickable(element);
    }

    public void enterValue(WebElement element, String value){
        element.sendKeys(value);
    }

    public void selectFromDropdownByValue(WebElement element,String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void scrollToElement(WebElement element){
        waitForElementVisibility(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",element);
    }

    public void acknowledgeAlert(){
        try{
            Alert alert = webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        }
        catch (Exception e){
            System.out.println("exception"+e);
        }

    }
  public String verifyErrorMessage(WebElement element){
    return getText(element);
  }

}
