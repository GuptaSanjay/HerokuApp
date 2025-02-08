package com.utility.Pages;

import com.utility.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends InitializeElements {

    protected SeleniumWrappers seleniumWrappers;
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        //this.driver = driver;
        this.seleniumWrappers = new SeleniumWrappers(driver);
        //PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = ".//h2[text() = \"Available Examples\"]")
    protected WebElement heading;

    public String verifyPageheading(){
        return seleniumWrappers.getText(heading);
    }

    public String getPageTitle(){
       return seleniumWrappers.getTitle();
    }
}
