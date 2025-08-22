package com;

import com.engine.DriverManger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTest {
    protected static WebDriver driver;
    private String link = "https://automationexercise.com/";

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setupDriver(String browser){
      System.out.println("Starting the Driver");
      driver = DriverManger.getInstance(browser).getDriver();
      System.out.println(Thread.currentThread().getName()+"Thread name"+"with Thread ID "+Thread.currentThread().threadId());
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        openWeb(link);
    }

    @BeforeClass  (alwaysRun = true)
    public void closeDriver() throws InterruptedException {
        DriverManger.stopEngine();
    }

    public void openWeb(String link){
        driver.manage().window().maximize();
        driver.get(link);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

}
