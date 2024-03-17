package frontgate.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frontgate.Data.LogInCasesDataReader;
import frontgate.pageObjectModel.ShoppingCartViewPage;
import frontgate.testComponents.BaseTest;

public class ShoppingCartLogin extends BaseTest {

	@Test(dataProvider = "loginData")
	public void LoginViaShoppingCart(String email, String password) throws InterruptedException, IOException {

		ShoppingCartViewPage shoppingCartLogin = new ShoppingCartViewPage(driver);
		// navigate to login shopping cart page
		shoppingCartLogin.GoTo();
		// fill and submit the login form
		shoppingCartLogin.Login(email, password);
		// check login status and error messege depend on correctness value
		shoppingCartLogin.checkLogin(email, password);
	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		LogInCasesDataReader dataReader = new LogInCasesDataReader();
		return dataReader.getData();
	}
}
