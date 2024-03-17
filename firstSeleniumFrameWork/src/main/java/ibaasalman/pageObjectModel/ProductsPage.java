package ibaasalman.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ibaasalman.abstractComp.AbstractComponent;

public class ProductsPage extends AbstractComponent {

	WebDriver Driver;

	public ProductsPage(WebDriver Driver) {
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	public WebElement findProductByName(String name) {
		return Driver.findElements(By.className("mb-3")).stream()
			   .filter(prod -> prod.findElement(By.tagName("b"))
			   .getText().equals(name)).findFirst().orElse(null);
	}

	public void addProductToCart (String name) {
		findProductByName(name).findElement(By.cssSelector(".mb-3 .w-10")).click();
		waitUntillToastVisible();
		waitUntilLoadingInvisible();
	}
}
