package com.engine;

import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;

public class DriverManager {

    private static volatile DriverManager driverMangerInstance;
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    @Setter
    private static String browser;

    private DriverManager(){
    }

    private void initEngine(String browser){
        switch (browser.toLowerCase()){
            case "chrome":{
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
            case "firefox":
                threadLocal.set(new FirefoxDriver());
                break;
            default:{
                throw new IllegalArgumentException("Browser is not supported");
            }
        }
    }

    public static DriverManager getInstance(){
        if(driverMangerInstance==null){
            synchronized (DriverManager.class){
                if(driverMangerInstance == null){
                    driverMangerInstance = new DriverManager();
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
