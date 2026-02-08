package com.test.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utility.Report.ExtentReportManager;
import com.utility.Report.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReportManager.getExtentObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public void onTestStart(ITestResult result) {
        String testDescription = (result.getMethod().getDescription() !=null && !result.getMethod().getDescription().isEmpty()) ?
                result.getMethod().getDescription():
                result.getMethod().getMethodName();
        test = extent.createTest(testDescription);
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            extentTest.get().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot(driver),result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());
        WebDriver driver = null;
        try {
           driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath = null;
        try {
            filePath = ScreenshotUtil.captureScreenshot(driver,result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

    }

    public void onTestSkipped(ITestResult result) {
    }

//    public void onStart(ITestContext context) {
//    }
//
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
