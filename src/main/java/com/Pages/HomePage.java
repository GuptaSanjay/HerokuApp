package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import com.utility.Report.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends InitializeElements {

    protected SeleniumWrappers wrappers;
    protected WebDriver driver;
    private static final Logger logger = LoggerUtil.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
        this.wrappers = new SeleniumWrappers(driver);
        logger.info("HomePage initialized with driver: {}", driver);
    }

    @FindBy(xpath = ".//div[@class=\"shop-menu pull-right\"]//ul//li")
    protected List<WebElement> headerOption;

    @FindBy(tagName ="a")
    protected WebElement links;

    @FindBy(xpath = ".//a[@href=\"/\"]//img")
    private WebElement headerText;

    @FindBy(xpath = ".//a[text()=' Home']")
    private WebElement homePageButton;

    @FindBy(xpath = "//a[text()=' Products']")
    private WebElement productsMenuButton;


    public String verifyPageHeading(){
        String heading = wrappers.getAttributeValue(headerText,"alt");
        logger.info("Verifying page heading: {}", heading);
        return heading;
    }

    public String getPageTitle(){
       String title = wrappers.getTitle();
       logger.info("Getting page title: {}", title);
       return title;
    }

    public List<String> getHeaderOption(){
        List<String> headerList = new ArrayList<>();
        headerList = wrappers.getListValue(headerOption);
        return headerList;
    }

    public boolean verifyHomePage(){
       boolean res = false;
        try {
            String ifColored = wrappers.getAttributeValue(homePageButton, "style");
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

    public void clickOnMenuButton(String homePageButtonName) throws InterruptedException {
        wrappers.clickOnDynamicButton(homePageButtonName);
        wrappers.handleAdvertisement();
    }

    public void goToProductsPage() throws InterruptedException {
        wrappers.click(productsMenuButton);
        wrappers.handleAdvertisement();
        Thread.sleep(500);
    }
}
