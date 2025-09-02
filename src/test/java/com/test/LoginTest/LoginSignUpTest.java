package com.test.LoginTest;

import com.BaseTest;
import com.Pages.HomePage;
import com.Pages.LoggedInHomePage;
import com.Pages.LoginSignUpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginSignUpTest extends BaseTest {

    private LoginSignUpPage loginSignUpPage;
    private HomePage homePage;
    private LoggedInHomePage loggedInHomePage;


    @BeforeMethod
    public void driverSetup(){
        loginSignUpPage = new LoginSignUpPage(driver);
        homePage = new HomePage(driver);
        loggedInHomePage = new LoggedInHomePage(driver);
    }


    @Test(dataProvider = "validLoginData")
    public void loginWithValidUser(String username,String password) throws InterruptedException{
      homePage.clickOnButtonFromHomePage("Signup / Login");
      loginSignUpPage.login(username,password);
      String user = loggedInHomePage.getLoggedInUserName();
      Assert.assertEquals(user, "sanjay");
      loggedInHomePage.logOut();
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
