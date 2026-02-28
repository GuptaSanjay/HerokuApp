package com.test;

import com.engine.DriverManager;
import com.utility.Report.LoggerUtil;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LoggerUtil.getLogger(BaseTest.class);

    {
      try {
        fis = new FileInputStream(globalDataPath);
        properties.load(fis);
        logger.info("Loaded global data from: {}", globalDataPath);
      } catch (IOException e) {
        logger.error("Failed to load global data: {}", e.getMessage());
        throw new RuntimeException(e);
      }
    }
    private final String link = properties.getProperty("baseURL");

    private String getBrowserType(String browser){
      String systemBrowser = System.getProperty("browser");
      if (systemBrowser != null && !systemBrowser.isEmpty()) {
        logger.info("Using browser from system property: {}", systemBrowser);
        return systemBrowser;
      }

      if (browser != null && !browser.isEmpty()) {
        logger.info("Using browser from test parameter: {}", browser);
        return browser;
      }

      logger.warn("No browser specified, defaulting to Chrome");
      return "Chrome";
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setupDriver(@Optional("Chrome") String browser) throws FileNotFoundException {
      System.out.println("**************  Starting the Driver  ****************");
      browser = getBrowserType(browser);
      DriverManager.setBrowser(browser);
      driver = DriverManager.getInstance().getDriver();
      System.out.println(Thread.currentThread().getName()+"Thread name"+"with Thread ID "+Thread.currentThread().threadId());
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        openWeb(link);
    }

    @AfterMethod (alwaysRun = true)
    public void closeDriver() throws InterruptedException {
      System.out.println("*****************  Quiting the driver  ****************");
      DriverManager.stopEngine();
    }

    public void openWeb(String link){
        driver.manage().window().maximize();
        driver.get(link);
    }

}
