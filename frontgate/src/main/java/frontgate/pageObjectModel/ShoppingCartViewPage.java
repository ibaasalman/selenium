package frontgate.pageObjectModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ShoppingCartViewPage {
	WebDriver Driver;
	FileInputStream fis = new FileInputStream(
			new File(System.getProperty("user.dir")) + "\\src\\main\\java\\Resourses\\GlobalData.properties");
	Properties prop = new Properties();

	public ShoppingCartViewPage(WebDriver Driver) throws IOException {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
		prop.load(fis);
	}

	@FindBy(id = "gwt-sign-in-modal")
	WebElement emailInputField;

	@FindBy(id = "passwordReset")
	WebElement passwordInputField;

	@FindBy(id = "logonButton")
	WebElement logInButton;

	public void GoTo() {
		Driver.get(prop.getProperty("baseUrl"));
		Driver.findElement(By.id("details-button")).click();
		Driver.findElement(By.id("proceed-link")).click();
	}

	public void Login(String email, String password) {
		emailInputField.sendKeys(email);
		passwordInputField.sendKeys(password);
		logInButton.click();
	}

	public void checkLogin(String email, String Password) throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> errorMsgs = Driver.findElements(By.className("errortxt"));

		// RegEx pattren to check if email is valid format or not
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher mat = pattern.matcher(email);

		// check if email is empty or if its not valid email format
		if (email.trim().isEmpty()) {
			Boolean result = errorMsgs.stream()
					.anyMatch(msg -> msg.getText().equalsIgnoreCase("Error: Please enter Email Address."));
			Assert.assertTrue(result);
		} else if (!mat.matches()) {
			Boolean result = errorMsgs.stream().anyMatch(
					msg -> msg.getText().equalsIgnoreCase("Error: Please enter Email Address in valid format."));
		}

		// check if password is empty
		if (Password.trim().isEmpty()) {
			Boolean result = errorMsgs.stream()
					.anyMatch(msg -> msg.getText().equalsIgnoreCase("Error: Please enter Current Password."));
			Assert.assertTrue(result);
		}

		System.out.println("size : " + errorMsgs.size());
		// check if email and password are valid or not and assert login or error msg
		if (!email.trim().isEmpty() && !Password.trim().isEmpty() && mat.matches() && errorMsgs.size() == 0) {

			Thread.sleep(1000);
			WebElement signOutLink = Driver.findElement(By.cssSelector("a#login span"));
			Assert.assertTrue(signOutLink.getText().equalsIgnoreCase("Sign Out"));

		} else if (!email.trim().isEmpty() && !Password.trim().isEmpty() && mat.matches()) {
			Boolean result = errorMsgs.stream().anyMatch(msg -> msg.getText()
					.equalsIgnoreCase("Email/Password you entered is not correct. Please try again."));
			Assert.assertTrue(result);

		}

	}
}
