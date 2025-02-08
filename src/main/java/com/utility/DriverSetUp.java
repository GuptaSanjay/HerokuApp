package com.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static java.lang.Thread.sleep;

public class DriverSetUp {
    protected WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        openWeb("https://the-internet.herokuapp.com/");
    }

    @AfterClass
    public void closeDriver() throws InterruptedException {
        sleep(5000);
        driver.quit();
    }

    public void openWeb(String link){
        driver.get(link);
    }

}
