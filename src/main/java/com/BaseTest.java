package com;

import com.engine.DriverManger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BaseTest {
    protected static WebDriver driver;
    private String browser = "Chrome";
    private String link = "https://automationexercise.com/";

    @BeforeClass
    public void setupDriver(){
        driver = DriverManger.getInstance(browser).getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        openWeb(link);
    }

    @AfterClass
    public void closeDriver() throws InterruptedException {
        DriverManger.stopEngine();
    }

    public void openWeb(String link){
        driver.manage().window().maximize();
        driver.get(link);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

}
