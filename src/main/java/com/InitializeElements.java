package com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InitializeElements {
    protected WebDriver driver;

    protected InitializeElements(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public InitializeElements() {
    }
}
