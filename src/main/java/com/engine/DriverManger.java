package com.engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManger {

    private static DriverManger driverMangerInstance;
    private static WebDriver driver;

    private DriverManger(){
    }

    private void initEngine(String browser){
        switch (browser){
            case "Chrome":{
                driver  = new ChromeDriver();
                break;
            }
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            default:{
                throw new IllegalArgumentException("Browser is not supported");
            }
        }
    }

    public static DriverManger getInstance(String brower){
        if(driverMangerInstance==null){
            synchronized (DriverManger.class){
                if(driverMangerInstance == null){
                    driverMangerInstance = new DriverManger();
                }
            }
        }
        if(driver==null){
            driverMangerInstance.initEngine(brower);
        }
        return driverMangerInstance;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public static void stopEngine(){
        if(driver!=null){
            driver.quit();
        }
    }
}
