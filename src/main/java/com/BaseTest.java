package com;

import com.engine.DriverManger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTest {
    protected static WebDriver driver;
    private String link = "https://automationexercise.com/";

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setupDriver(@Optional("Chrome") String browser){
      System.out.println("**************  Starting the Driver  ****************");
      driver = DriverManger.getInstance(browser).getDriver();
      System.out.println(Thread.currentThread().getName()+"Thread name"+"with Thread ID "+Thread.currentThread().threadId());
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        openWeb(link);
    }

    @AfterMethod (alwaysRun = true)
    public void closeDriver() throws InterruptedException {
      System.out.println("*****************  Quiting the driver  ****************");
      DriverManger.stopEngine();
    }

    public void openWeb(String link){
        driver.manage().window().maximize();
        driver.get(link);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

}
