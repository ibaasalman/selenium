package ibaasalman.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver Driver;
	public LandingPage(WebDriver Driver) {
		super();
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(id="userEmail")
	WebElement EmailField;
	
	@FindBy(id="userPassword")
	WebElement PasswordField;
	
	@FindBy(id="login")
	WebElement SubmitBTN;
	
	public void GoTo() {
		Driver.get("https://rahulshettyacademy.com/client");	
	}
	
	public void LogIn(String userName , String Password) {
		EmailField.sendKeys(userName);
		PasswordField.sendKeys(Password);
		SubmitBTN.click();
	}
	
	
	
}
