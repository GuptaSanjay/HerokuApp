package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class CreateAccountPage extends InitializeElements {

    private SeleniumWrappers seleniumWrappers;

    public CreateAccountPage(WebDriver driver){
        super(driver);
        this.seleniumWrappers = new SeleniumWrappers(driver);
    }

    @FindBy(xpath = ".//b[.=\"Enter Account Information\"]")
    private WebElement accountInformationPage;

    @FindBy(xpath = ".//input[@id=\"id_gender1\"]")
    private  WebElement titleMr;

    @FindBy(xpath = ".//select[@id=\"days\"]")
    private WebElement dob_days;

    @FindBy(xpath = ".//select[@id=\"months\"]")
    private WebElement dob_months;

    @FindBy(xpath = ".//select[@id=\"years\"]")
    private WebElement dob_years;

    @FindBy(xpath = ".//input[@id=\"newsletter\"]")
    private WebElement newsletter;

    @FindBy(xpath = ".//input[@id=\"optin\"]")
    private WebElement offer;

    @FindBy(xpath = ".//input[@id=\"password\"]")
    private WebElement paswd;

    @FindBy(xpath = "//input[@id=\"first_name\"]")
    private WebElement first_name;

    @FindBy(xpath = "//input[@id=\"last_name\"]")
    private WebElement last_name;

    @FindBy(xpath = "//select[@id=\"country\"]")
    private WebElement country;

    @FindBy(xpath = "//input[@id=\"state\"]")
    private WebElement state;

    @FindBy(xpath = "//input[@id=\"address1\"]")
    private WebElement address1;

    @FindBy(xpath = "//input[@id=\"city\"]")
    private WebElement city;

    @FindBy(xpath = "//input[@id=\"zipcode\"]")
    private WebElement zipcode;

    @FindBy(xpath = "//input[@id=\"mobile_number\"]")
    private WebElement mobile_number;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement createButton;


    public CreateAccountPage enterUserDetails(Map<String,String> details) throws InterruptedException {
        fillUserDetails(details);
        return this;
    }

    private void fillUserDetails(Map<String, String> details) throws InterruptedException {
        SeleniumWrappers.waitForElementVisibility(accountInformationPage);
        first_name.sendKeys(details.get("firstName"));
        last_name.sendKeys(details.get("lastName"));
        paswd.sendKeys(details.get("password"));
        address1.sendKeys(details.get("Address"));
        //seleniumWrappers.shortWait();
        SeleniumWrappers.selectFromDropdownByValue(country,details.get("country"));
        state.sendKeys(details.get("state"));
        city.sendKeys(details.get("city"));
        zipcode.sendKeys(details.get("zipcode"));
        mobile_number.sendKeys(details.get("mobile"));
    }

    public AccountCreatedPage clickOnCreateAccount() throws InterruptedException {
        SeleniumWrappers.shortWait();
        SeleniumWrappers.scrollToElement(createButton);
        createButton.click();
        return new AccountCreatedPage(driver);
    }

}
