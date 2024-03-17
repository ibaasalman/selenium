package frontgate.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public WebDriver driver = null;

	@BeforeMethod
	public WebDriver initializeDriver() throws IOException {
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir"))+"\\src\\main\\java\\Resourses\\GlobalData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browserName = prop.getProperty("Browser");

		if (browserName.equalsIgnoreCase("chrome")) {
	        driver = new ChromeDriver();
	    } else if (browserName.equalsIgnoreCase("Edge")) {
	        driver = new EdgeDriver();
	    } else if (browserName.equalsIgnoreCase("FireFox")) {
	        driver = new FirefoxDriver();
	    }
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		return driver;
	}

	@AfterMethod
	public void close() {
		driver.close();
	}

}
