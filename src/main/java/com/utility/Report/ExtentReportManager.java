package com.utility.Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utility.DateUtil;

import java.nio.file.FileSystems;
import java.util.Date;

public class ExtentReportManager {
  private static ExtentReports extent;
  private static String DATE = DateUtil.currentDateExtentReport();
  private static String USER_DIR = System.getProperty("user.dir");
  private static String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
  private static String REPORT_PATH = USER_DIR+ FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test"+ FILE_SEPARATOR+ "Artifacts"+ FILE_SEPARATOR + DATE + "index.html";

  public static ExtentReports getExtentObject(){
    ExtentSparkReporter reporter = new ExtentSparkReporter(REPORT_PATH);
    reporter.config().setDocumentTitle("Automation Report");
    reporter.config().setTimeStampFormat("EEEE, MMMM  dd, yyyy, hh:mm: a '('ZZZ')'");
    reporter.config().setReportName("Sample Extent Report");
    extent = new ExtentReports();
    extent.attachReporter(reporter);
    extent.setSystemInfo("Tester","Sanjay Gupta");
    return extent;
  }
}
