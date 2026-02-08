package com.utility.Report;

import com.aventstack.extentreports.ExtentReports;
import com.utility.DateUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;


public class ScreenshotUtil {

    private static ExtentReports extent;
    private static String DATE = DateUtil.currentDateExtentReport();
    private static String USER_DIR = System.getProperty("user.dir");
    private static String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    private static String SCREENSHOT_PATH = USER_DIR+ FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test"+ FILE_SEPARATOR+ "Artifacts";


    public static String captureScreenshot(WebDriver driver, String testCaseName) throws IOException {
        if(!(driver instanceof TakesScreenshot)){
            throw new IllegalArgumentException("Driver does not support TakesScreenshot");
        }
        File folder = new File(SCREENSHOT_PATH+FILE_SEPARATOR+"screenshots");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = folder+FILE_SEPARATOR+testCaseName+".png";
        File destinationFile = new File(filePath);
        FileUtils.copyFile(source, destinationFile);
        return filePath;
    }

    public static String takeScreenshot(WebDriver driver) throws IOException {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
