package TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	public WebDriver driver;

	@BeforeMethod
	public void goTo() throws IOException {
		Properties prop = new Properties();
	    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\ibaasalman\\ballarddesigns\\Resources\\GlobalData.properties");
	    prop.load(fis);
	    String browserName = prop.getProperty("Browser");

	    if (browserName.equalsIgnoreCase("chrome")) {
	        driver = new ChromeDriver();
	    } else if (browserName.equalsIgnoreCase("Edge")) {
	        driver = new EdgeDriver();
	    } else if (browserName.equalsIgnoreCase("FireFox")) {
	        driver = new FirefoxDriver();
	    }

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.manage().window().maximize();

	    driver.get(prop.getProperty("BaseUrl"));
	    driver.findElement(By.id("details-button")).click();
	    driver.findElement(By.id("proceed-link")).click();
	}

	@AfterMethod
	public void close() {
		driver.close();
	}

	public void checkLogo() {
		WebElement logo = driver.findElement(By.cssSelector(".logo-anchor img"));
		// Javascript executor to check image is loaded
		Boolean p = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
				+ "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", logo);
		Assert.assertTrue(p);
	}
}
