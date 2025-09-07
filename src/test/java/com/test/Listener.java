package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.utility.Report.ExtentReportManager.getExtentObject;

public class Listener implements ITestListener {

  private static final Logger log = LoggerFactory.getLogger(Listener.class);
  ExtentTest test;
  ExtentReports extent = getExtentObject();
  ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
  //runs only once if a class has multiple tests
  public void onStart(ITestContext context) {
    log.info("execution starting");
  }

  //runs every time before every test method
  public void onTestStart(ITestResult result) {
    test = extent.createTest(result.getMethod().getMethodName());
    extentTest.set(test);

  }

  public void onTestSuccess(ITestResult result) {
    extentTest.get().log(Status.PASS,result.getMethod().getMethodName()+ " test passed");
  }

  public void onTestFailure(ITestResult result) {
    extentTest.get().log(Status.FAIL, result.getMethod().getMethodName()+" test failed");
    extentTest.get().fail(result.getThrowable());
  }

  public void onTestSkipped(ITestResult result) {
  }

  public void onFinish(ITestContext context) {
    extent.flush();
  }

}
