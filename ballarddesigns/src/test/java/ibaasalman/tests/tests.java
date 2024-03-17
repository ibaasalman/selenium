package ibaasalman.tests;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import ibaasalman.ballarddesigns.PageObjectModels.HomePage;
import ibaasalman.ballarddesigns.PageObjectModels.SignInPage;

public class tests extends BaseTest {

	@Test
	public void Login() {
		String userEmail = "test@automation.com";
		String userPassword = "test@123";
		String userName = "firstAutomation";

		//Assert that the logo img is loaded
		checkLogo();
		
		// Navigate to login Page
		SignInPage mySignInPage = new SignInPage(driver);
		mySignInPage.goTo();
		// Assert the value of welcome msg in login page
		mySignInPage.checkWelcomeMsg();
		// Login using valid credentials
		mySignInPage.logIn(userEmail, userPassword);

		HomePage myHomePage = new HomePage(driver);
		// Assert myAccount Link
		myHomePage.checkMyAccountLink();
		// Assert the username is exist in welcome message
		myHomePage.checkUserName(userName);
	}

}
