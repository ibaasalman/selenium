package ibaasalman.ballarddesigns.PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#myAccount > a >span")
	WebElement MyAccountLink;
	
	@FindBy(id="welcome")
	WebElement welcomeMsg;
	
	//check and assert the myAccount Link is displayed
	public void checkMyAccountLink() {
		Assert.assertTrue(MyAccountLink.isDisplayed());
	}
	
	//check and assert the username is exist in welcome message
	public void checkUserName(String userName) {
		Assert.assertTrue(welcomeMsg.getText().contains(userName));
	}
}
