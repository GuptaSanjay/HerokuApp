package com.engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;

public class DriverManger {

    private static volatile DriverManger driverMangerInstance;
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();


    private DriverManger(){
    }

    private void initEngine(String browser){
        switch (browser){
            case "Chrome":{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-software-rasterizer");
                options.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false
                ));
                threadLocal.set(new ChromeDriver(options))  ;
                break;
            }
            case "Firefox":
                threadLocal.set(new FirefoxDriver());
                break;
            default:{
                throw new IllegalArgumentException("Browser is not supported");
            }
        }
    }

    public static DriverManger getInstance(String browser){
        if(driverMangerInstance==null){
            synchronized (DriverManger.class){
                if(driverMangerInstance == null){
                    driverMangerInstance = new DriverManger();
                }
            }
        }
        if(threadLocal.get()==null){
            driverMangerInstance.initEngine(browser);
        }
        return driverMangerInstance;
    }

    public WebDriver getDriver(){
        return threadLocal.get();
    }

    public static void stopEngine(){
        if(threadLocal.get()!=null){
            threadLocal.get().quit();
            threadLocal.remove();
        }
    }
}
