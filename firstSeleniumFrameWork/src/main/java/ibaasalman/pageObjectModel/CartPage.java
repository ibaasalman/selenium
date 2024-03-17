package ibaasalman.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ibaasalman.abstractComp.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver Driver;

	public CartPage(WebDriver Driver) {
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;

	@FindBy(className = "cartSection")
	List<WebElement> cartElements;

	@FindBy(css=".subtotal .btn")
	WebElement cheackOut;
	
	public void NavigateToCart() {
		cart.click();
	}

	public Boolean isInTheCart(String Name) {
		return cartElements.stream().anyMatch(prod -> prod.findElement(By.tagName("h3")).getText().equals(Name));
	}

	public void cheackOut() {
		cheackOut.click();
	}
}
