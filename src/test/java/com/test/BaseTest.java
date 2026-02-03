package com.test;

import com.engine.DriverManger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Properties;


public class BaseTest {
    public WebDriver driver;
    private final String USER_DIR = System.getProperty("user.dir");
    private final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private final String globalDataPath = USER_DIR+SEPARATOR+"src"+SEPARATOR+"main"+SEPARATOR+"resources"+SEPARATOR+"GlobalData.properties";
    Properties properties = new Properties();
    FileInputStream fis;
    {
      try {
        fis = new FileInputStream(globalDataPath);
        properties.load(fis);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    private final String link = properties.getProperty("baseURL");

    private String getBrowserType(String browser){
      String systemBrowser = System.getProperty("browser");
      if (systemBrowser != null && !systemBrowser.isEmpty()) {
        return systemBrowser;
      }

      if (browser != null && !browser.isEmpty()) {
        return browser;
      }

      String configBrowser = properties.getProperty("browser");
      if (configBrowser != null && !configBrowser.isEmpty()) {
        return configBrowser;
      }
      return "No browser";
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setupDriver(@Optional("Chrome") String browser) throws FileNotFoundException {
      System.out.println("**************  Starting the Driver  ****************");
      //browser = System.getProperty("browser")!=null ? System.getProperty("browser") :properties.getProperty("browser");
      browser = getBrowserType(browser);
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
    }

}
