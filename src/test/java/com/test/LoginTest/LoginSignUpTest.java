package com.test.LoginTest;

import com.BaseTest;
import com.Pages.HomePage;
import com.Pages.LoginSignUpPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginSignUpTest extends BaseTest {

    private LoginSignUpPage loginSignUpPage;
    private HomePage homePage;


    @BeforeMethod
    public void driverSetup(){
        loginSignUpPage = new LoginSignUpPage(driver);
        homePage = new HomePage(driver);
    }


    @Test(dataProvider = "validLoginData")
    public void loginWithValidUser(String username,String password) throws InterruptedException{
        homePage.clickOnButtonFromHomePage("Signup / Login");
        loginSignUpPage.login(username,password);
    }

  @Test(dataProvider = "invalidLoginData")
  public void loginWithInvalidUser(String username,String password) throws InterruptedException{
    homePage.clickOnButtonFromHomePage("Signup / Login");
    loginSignUpPage.login(username,password);
    loginSignUpPage.verifyErrorMessage();
  }

  @DataProvider(name = "validLoginData")
  public Object[][] provideUserData(){
    return new Object[][]{
      {"sanjay.cloudtech@gmail.com","Test1234"}
    };
  }

  @DataProvider(name = "invalidLoginData")
  public Object[][] invalidUserData(){
    return new Object[][]{
      {"sanjay.cloudtech@gmail.com","Test12344"},
      {"sanjay.cloudtech1@gmail.com","Test1234"},
    };
  }


}
