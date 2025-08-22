package com.test;

import com.BaseTest;
import com.Pages.*;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.Map;

import static java.util.Map.entry;


public class UserRegistrationTest extends BaseTest {

    private LoginSignUpPage loginSignUpPage;
    private HomePage homePage;
    private CreateAccountPage createAccountPage;
    private AccountCreatedPage accountCreatedPage;
    private LoggedInHomePage loggedInHomePage;
    private DeleteAccountPage deleteAccountPage;

    Map<String, String> addressInfo = Map.ofEntries(
            entry("Address", "D-Block, 1100, J J Colony"),
            entry("country", "India"),
            entry("city", "Bawana"),
            entry("state", "Delhi"),
            entry("zipcode", "110039"),
            entry("mobile", "7299695507"),
            entry("firstName", "sanjay"),
            entry("lastName", "gupta"),
            entry("day", "28"),
            entry("month", "02"),
            entry("year", "1998"),
            entry("password", "Test1234")
    );


    @BeforeMethod
    public void driverSetUp() {
        loginSignUpPage = new LoginSignUpPage(driver);
        homePage = new HomePage(driver);
        createAccountPage = new CreateAccountPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        loggedInHomePage = new LoggedInHomePage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
    }

    @Test(description = "Verify user can sign up and can  delete account", dataProvider ="userData", alwaysRun = true)
    public void registerUser(String username, String email) throws InterruptedException {
        homePage.getPageTitle();
        homePage.verifyHomePage();
        homePage.clickOnButtonFromHomePage("Signup / Login");
        loginSignUpPage.fillFormForSignUp(username,email);
        loginSignUpPage.clickOnSignUp();
        createAccountPage.enterUserDetails(addressInfo);
        createAccountPage.clickOnCreateAccount();
        String text = accountCreatedPage.getAccountCreatedText();
        Assert.assertEquals(text, "ACCOUNT CREATED!");
        accountCreatedPage.continueToVerifyLoggedInUserPage();
        String user = loggedInHomePage.getLoggedInUserName();
        Assert.assertEquals(user, username);
        loggedInHomePage.logOut();
        
//        loggedInHomePage.clickOnDeleteAccount();
//        String deleteText = deleteAccountPage.getDeleteAccountConfirmation();
//        Assert.assertEquals(deleteText, "ACCOUNT DELETED!");
//        deleteAccountPage.continueButtonClick();
//        homePage.verifyHomePage();
    }


    @Test(description = "Delete registered user", dataProvider = "userData", alwaysRun = true, dependsOnMethods = "registerUser" )
    public void deleteUser(String username, String email) throws InterruptedException {
      homePage.getPageTitle();
      homePage.clickOnButtonFromHomePage("Signup / Login");
      loginSignUpPage.loginWithValidUserCred(email,addressInfo.get("password"));
      String user = loggedInHomePage.getLoggedInUserName();
      Assert.assertEquals(user, username);
      loggedInHomePage.clickOnDeleteAccount();
      String deleteText = deleteAccountPage.getDeleteAccountConfirmation();
      Assert.assertEquals(deleteText, "ACCOUNT DELETED!");
      deleteAccountPage.continueButtonClick();
      homePage.verifyHomePage();
    }

//    private static class InputData{
//        private final UserRegistration userRegistration;
//        public InputData(){
//            userRegistration = userFormData();
//        }
//
//        private UserRegistration userFormData() {
//            return UserRegistration.builder()
//                    .getfirstName(UserData.First_Name.getUserData())
//                    .build();
//
//        }
//}

  @DataProvider(name = "userData")
  public Object[][] provideUserData(){
      return new Object[][]{
        {"sanjay","sanjay.cloudtech@gmail.com"},
//        {"Raj","san568sliewn223test1@mailinator.com"}
//        {"Gupta","san568jlsflsk3238test2@mailinator.com"}
      };
  }
}
