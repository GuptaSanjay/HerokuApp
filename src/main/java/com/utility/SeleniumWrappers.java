package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWrappers extends DriverSetUp{

    protected WebDriver driver;
    protected int shortWait = 10;

    public SeleniumWrappers(WebDriver driver){
        this.driver=driver;
    }

    protected void setShortWait() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(this.shortWait));
    }

    public String getTitle(){
        return driver.getTitle();
    }

    protected void click(WebElement element){
        element.click();
    }

    protected void enterString(WebElement element, String string){
        element.sendKeys(string);
    }

    public String getText(WebElement element){
        waitForElementVisibility(element);
        return element.getText();
    }

    protected void waitForElementVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(shortWait)); // Updated to use Duration
        wait.until(ExpectedConditions.visibilityOf(element)); // Wait until element is visible
        //element.sendKeys(string);
    }

    private void waitUntileElementIsClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(shortWait));
        wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until element is clickable
        element.click();
    }
}
