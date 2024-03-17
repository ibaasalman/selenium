package ibaasalman.ballarddesigns.PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignInPage {

	WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="loginMyAccount")
	WebElement logInLink;
	
	@FindBy(css="#BDLoginMessageNormal p:nth-of-type(1)")
	WebElement welcomeMsg;
	
	@FindBy(id="logonId")
	WebElement emailInputField;
	
	@FindBy(id="logonPassword")
	WebElement passwordInputField;
	
	@FindBy(id="logonButton")
	WebElement submitLogInButton;
	
	//Navigate to signIn page
	public void goTo() {
		logInLink.click();
	}

	//Assert the value of welcome message
	public void checkWelcomeMsg() {
		Assert.assertEquals(welcomeMsg.getText(),
				"Welcome back! To access your account, please enter your email address and password and click Sign In.");
	}

	public void logIn(String userEmail, String userPassword) {
		emailInputField.sendKeys(userEmail);
		passwordInputField.sendKeys(userPassword);
		submitLogInButton.click();
	}
}
