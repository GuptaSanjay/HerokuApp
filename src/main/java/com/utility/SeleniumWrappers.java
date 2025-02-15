package com.utility;

import com.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumWrappers extends BaseTest {

    protected WebDriver driver;
    protected int shortWait = 10;
    protected int mediumWait = 20;
    protected WebDriverWait webDriverWait;

    public SeleniumWrappers(WebDriver driver){
        this.driver=driver;
    }

    private void waitForPageTOLoad(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("return document.readyState").toString().equals("complete");
    }

    private void shortWait() throws IllegalArgumentException, InterruptedException {
        Thread.sleep(shortWait);
    }

    protected void setShortWait() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(this.shortWait));
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void click(WebElement element){
        element.click();
    }

    public void enterString(WebElement element, String string){
        element.sendKeys(string);
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
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(shortWait)); // Updated to use Duration
        webDriverWait.until(ExpectedConditions.visibilityOf(element)); // Wait until element is visible
        //element.sendKeys(string);
    }

    public void waitUntileElementIsClickable(WebElement element){
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(shortWait));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until element is clickable
        element.click();
    }

    public void waitShortToClickable(WebElement element){
        webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(shortWait));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
