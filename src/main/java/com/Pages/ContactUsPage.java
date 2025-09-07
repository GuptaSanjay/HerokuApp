package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class ContactUsPage extends InitializeElements {
  SeleniumWrappers wrappers;

  public ContactUsPage(WebDriver driver){
    super(driver);
    wrappers = new SeleniumWrappers(driver);
  }

  @FindBy(xpath = "//form[@id='contact-us-form']/parent::div/../h2")
  private WebElement getInTouch;

  @FindBy(xpath = "//textarea[@id='message']")
  private WebElement textArea;

  @FindBy(xpath = "//input[@name='submit']")
  private WebElement chooseFile;

  @FindBy(xpath = "//input[@name='submit']")
  WebElement submit;

  @FindBy(xpath = "//div[@id='form-section']/parent::div/child::div[2]")
  private WebElement successAlert;

  @FindBy(xpath = "//div[@id='form-section']/a")
  private WebElement homePage;

  private void enterValueInField(String fieldName,String text){
    String value = getInputFieldValue(fieldName);
    WebElement inputField = driver.findElement(By.xpath("//form[@id='contact-us-form']/child::div"+"["+value+"]"+"/input"));
    inputField.sendKeys(text);
  }

  public String verifyContactUsPage(){
   return wrappers.getText(getInTouch);
  }

  public void fillFormDetails(Map<String,String> inputData){
    enterValueInField("Name",inputData.get("Name"));
    enterValueInField("Email",inputData.get("Email"));
    enterValueInField("Subject",inputData.get("Subject"));
    textArea.sendKeys(inputData.get("Message"));
  }

  public void chooseFile(String path){
    chooseFile.sendKeys(path);
  }

  public void clickOnSubmitToProceed() throws InterruptedException {
    wrappers.clickWhenElementClickable(submit);
    //Thread.sleep(500);
    wrappers.acknowledgeAlert();
  }

  public String getSuccessMessage(){
    return wrappers.getText(successAlert);
  }

  public void clickOnHomePage(){
    wrappers.clickWhenElementClickable(homePage);
    new HomePage(driver);
  }

  private String getInputFieldValue(String field) {
    String value =null;
    try {
      if (field.equalsIgnoreCase("Name")) {
        value = "1";
      }
      if (field.equalsIgnoreCase("Email")) {
        value = "2";
      }
      if (field.equalsIgnoreCase("Subject")) {
        value = "3";
      }
    } catch (Exception e) {
      throw new RuntimeException("Provided input field do not match to enter value in contact us form");
    }
    return value;
  }
}
