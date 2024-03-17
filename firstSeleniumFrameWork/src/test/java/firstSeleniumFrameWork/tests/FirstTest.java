package firstSeleniumFrameWork.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import firstSeleniumFrameWork.TestComponents.BaseTest;
import firstSeleniumFrameWork.data.DataReader;
import ibaasalman.pageObjectModel.CartPage;
import ibaasalman.pageObjectModel.CheckoutPage;
import ibaasalman.pageObjectModel.LandingPage;
import ibaasalman.pageObjectModel.ProductsPage;

public class FirstTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		String testName = "Purchasing-"+input.get("ProdName");
		LandingPage myLandingPage = new LandingPage(driver);
		myLandingPage.GoTo();
		myLandingPage.LogIn(input.get("Email"), input.get("Password"));

		ProductsPage myProductsPage = new ProductsPage(driver);
		myProductsPage.addProductToCart(input.get("ProdName"));

		CartPage myCartPage = new CartPage(driver);
		myCartPage.NavigateToCart();
		Assert.assertTrue(myCartPage.isInTheCart(input.get("ProdName")));
		myCartPage.cheackOut();

		CheckoutPage myCheckoutPage = new CheckoutPage(driver);
		myCheckoutPage.FillCartInfo(input.get("CreditCardNumber"), input.get("NameONCard"),
				input.get("ExpiredDateMonth"), input.get("ExpiredDateYear"), input.get("CSV"));
		myCheckoutPage.SetCountryAndSubmit(input.get("Country"));

		String confirmationMsg = driver.findElement(By.className("hero-primary")).getText();
		getScreenshot(testName,driver);
		Assert.assertEquals(confirmationMsg, "THANKYOU FOR THE ORDER.");

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		DataReader reader = new DataReader();
		List<HashMap<String, String>> JSONdata = reader.getDataFromJSON(System.getProperty("user.dir")
				+ "\\src\\test\\java\\firstSeleniumFrameWork\\data\\PurchaseProducts.json");

		return new Object[][] { { JSONdata.get(0) }, { JSONdata.get(1) }, { JSONdata.get(2) } };
	}

}
