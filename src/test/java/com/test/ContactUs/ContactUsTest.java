package com.test.ContactUs;

import com.test.BaseTest;
import com.Pages.ContactUsPage;
import com.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.nio.file.FileSystems;
import java.util.Map;

import static java.util.Map.entry;

public class ContactUsTest extends BaseTest {
  
  private ContactUsPage contactUsPage;
  private HomePage homePage;
  private static String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
  private static String USER_DIR  =System.getProperty("user.dir");
  private static String REPORT_PATH = USER_DIR + FILE_SEPARATOR + "Reports" + FILE_SEPARATOR + "Wed_Aug_27_16_50_40_IST_2025index.html";

  Map<String, String> map = Map.ofEntries(
    entry("Name","TestUser1"),
    entry("Email","sanjay.cloudtech@gmail.com"),
    entry("Subject","testing contact us"),
    entry("Message","testing text area"),
    entry("GetInTouch","GET IN TOUCH"),
    entry("successMessage","Success! Your details have been submitted successfully.")
  );

//  @DataProvider
//  public static Object[][] formData() {
//    return new Object[][]{
//      //read json and return map
//    };
//  }



  @BeforeMethod
  public void initializeObject(){
    contactUsPage = new ContactUsPage(driver);
    homePage =new HomePage(driver);
  }
  
  @Test(description = "Verify user is able to submit contact us form")
  public void verifyContactUsPage() throws InterruptedException {
    homePage.getPageTitle();

    homePage.verifyHomePage();
    homePage.clickOnMenuButton("Contact us");
    String actual = contactUsPage.verifyContactUsPage();
    Assert.assertEquals(actual,map.get("GetInTouch"),"Contact  page  is not displayed");
    contactUsPage.fillFormDetails(map);
    contactUsPage.chooseFile(REPORT_PATH);
    contactUsPage.clickOnSubmitToProceed();
    String message = contactUsPage.getSuccessMessage();
    Assert.assertEquals(message,map.get("successMessage"),"Unable to submit contact us form");
//    contactUsPage.clickOnHomePage();
//    homePage.verifyHomePage();
  }
}
