package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends InitializeElements {

    protected SeleniumWrappers seleniumWrappers;
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.seleniumWrappers = new SeleniumWrappers(driver);
    }

    @FindBy(xpath = ".//div[@class=\"shop-menu pull-right\"]//ul//li")
    protected List<WebElement> headerOption;

    @FindBy(tagName ="a")
    protected WebElement links;

    @FindBy(xpath = ".//a[@href=\"/\"]//img")
    private WebElement headerText;

    @FindBy(xpath = ".//a[text()=' Home']")
    private WebElement homePageButton;


    public String verifyPageHeading(){
        return seleniumWrappers.getAttributeValue(headerText,"alt");
    }

    public String getPageTitle(){
       return seleniumWrappers.getTitle();
    }

    public List<String> getHeaderOption(){
        List<String> headerList = new ArrayList<>();
        headerList = seleniumWrappers.getListValue(headerOption);
        return headerList;
    }

    public boolean verifyHomePage(){
       boolean res = false;
        try {
            String ifColored = seleniumWrappers.getAttributeValue(homePageButton, "style");
            if (ifColored.isEmpty()) {
                res = false;
            }
            else
                res = true;
        }
        catch (ArithmeticException e) {
            System.out.println("Home Page is not clicked"+e);
        }
            return res;
    }

    public void clickOnButtonFromHomePage(String homePageButtonName) throws InterruptedException {
        seleniumWrappers.shortWait();
        seleniumWrappers.clickOnDynamicButton(homePageButtonName);

    }

}
