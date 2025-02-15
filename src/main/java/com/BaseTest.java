package com;

import com.engine.DriverManger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;
    private String browser = "Chrome";
    private String link = "https://automationexercise.com/";

    @BeforeSuite
    public void setupDriver(){
        driver = DriverManger.getInstance(browser).getDriver();
        openWeb(link);
    }

    @AfterSuite
    public void closeDriver() throws InterruptedException {
        DriverManger.stopEngine();
    }

    public void openWeb(String link){
        driver.get(link);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

}
