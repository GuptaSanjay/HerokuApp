package com.utility;


import com.utility.Report.LoggerUtil;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LoggerUtil.getLogger(SeleniumWrappers.class);

    public SeleniumWrappers(WebDriver driver){
        this.driver =driver;
        logger.info("SeleniumWrappers initialized with driver: {}", driver);
    }

    private void waitForPageTOLoad(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("return document.readyState").toString().equals("complete");
    }

     public void shortWait() throws IllegalArgumentException, InterruptedException {
        logger.debug("Waiting for {} seconds", shortWait);
        Thread.sleep(Duration.ofSeconds(shortWait));
    }

    public String getTitle(){
        String title = driver.getTitle();
        logger.info("Page title fetched: {}", title);
        return title;
    }

    public void click(WebElement element){
        logger.info("Clicking on element: {}", element);
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

    public void handleAdvertisement() {
        try {
            // 1. Try to close ad overlays in main DOM
            List<By> adSelectors = List.of(
                By.xpath("//*[contains(@aria-label,'Close') or contains(@class,'close') or contains(text(),'×') or contains(text(),'Close') or @id='dismiss-button']"),
                By.cssSelector(".close, .close-button, .ad-close, [aria-label='close'], [aria-label='dismiss']")
            );
            for (By selector : adSelectors) {
                List<WebElement> closeButtons = driver.findElements(selector);
                for (WebElement btn : closeButtons) {
                    if (btn.isDisplayed() && btn.isEnabled()) {
                        btn.click();
                        logger.info("Closed advertisement overlay in main DOM.");
                        Thread.sleep(500);
                    }
                }
            }
            // 2. Try to close ad overlays inside iframes
            List<WebElement> adFrames = driver.findElements(By.xpath("//iframe[contains(@id,'aswift') or contains(@title,'Advertisement') or contains(@name,'google_ads_iframe') or contains(@src,'doubleclick')]"));
            for (WebElement adFrame : adFrames) {
                try {
                    driver.switchTo().frame(adFrame);
                    for (By selector : adSelectors) {
                        List<WebElement> closeButtons = driver.findElements(selector);
                        for (WebElement btn : closeButtons) {
                            if (btn.isDisplayed() && btn.isEnabled()) {
                                btn.click();
                                logger.info("Closed advertisement overlay inside iframe.");
                                Thread.sleep(500);
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.warn("Error handling ad in iframe: {}", e.getMessage());
                } finally {
                    driver.switchTo().defaultContent();
                }
            }
        } catch (Exception e) {
            logger.warn("No advertisement overlay handled or error occurred: {}", e.getMessage());
            driver.switchTo().defaultContent();
        }
    }
}
