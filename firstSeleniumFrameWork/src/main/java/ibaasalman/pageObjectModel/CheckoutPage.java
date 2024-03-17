package ibaasalman.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ibaasalman.abstractComp.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver Driver;

	public CheckoutPage(WebDriver Driver) {
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(css=".form__cc .text-validated")
	WebElement creditCardField;
	
	@FindBy(css="select:nth-of-type(1)")
	WebElement monthField;
	
	@FindBy(css="select:nth-of-type(2)")
	WebElement yearField;
	
	@FindBy(css="div:nth-of-type(2) > .input.txt")
	WebElement csvField;
	
	@FindBy(css=".form__cc .row:nth-of-type(3) .input")
	WebElement nameOnCardField;
	
	@FindBy(css=".form-group .text-validated")
	WebElement countryField;
	
	@FindBy(css=".list-group-item .ng-star-inserted")
	WebElement selectdCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitBtn;
	
	public void FillCartInfo(String CreditCardNumber, String NameONCard, String ExpiredDateMonth,
			String ExpiredDateYear, String CSV) {

		creditCardField.clear();
		creditCardField.sendKeys(CreditCardNumber);	
		Select monthSelect = new Select(monthField);
		monthSelect.selectByVisibleText(ExpiredDateMonth);
		Select YearSelect = new Select(yearField);
		YearSelect.selectByVisibleText(ExpiredDateYear);
		csvField.sendKeys(CSV);
		nameOnCardField.sendKeys(NameONCard);

	}
	
	public void SetCountryAndSubmit(String Country) {
		countryField.sendKeys(Country);
		selectdCountry.click();
		submitBtn.click();
	}

}
