package com.Pages;

import com.InitializeElements;
import com.utility.SeleniumWrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginSignUpPage extends InitializeElements {

    public LoginSignUpPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = ".//button[text()='Login']/..//input[2]")
    private WebElement emmailForLogin;

    @FindBy(xpath = ".//button[text()='Login']/..//input[3]")
    private WebElement passworForLogin;

    @FindBy(xpath = ".//button[text()='Signup']/..//input[2]")
    private WebElement userName;

    @FindBy(xpath = ".//button[text()='Signup']/..//input[3]")
    private WebElement email;

    @FindBy(xpath = "//button[text()='Signup']")
    private WebElement signUp;

  @FindBy(xpath = "//button[text()='Login']")
  private WebElement login;


    public void loginWithValidUserCred(String username, String password){
        fillLoginPage(username,password);
        login.click();
      new LoggedInHomePage(driver);
    }

    public LoginSignUpPage enterInvalidUserCred(String username, String password){
        fillLoginPage(username,password);
        return this;
    }

    public LoginSignUpPage fillFormForSignUp(String username, String email) throws InterruptedException {
        fillSignUpForm(username,email);
        SeleniumWrappers.shortWait();
        return this;
    }

    public CreateAccountPage clickOnSignUp(){
        signUp.click();
        return new CreateAccountPage(driver);
    }


    private void fillSignUpForm(String username,String Email) {
        userName.sendKeys(username);
        email.sendKeys(Email);
    }

    private void fillLoginPage(String username, String password){
        emmailForLogin.sendKeys(username);
        passworForLogin.sendKeys(password);
    }


}
