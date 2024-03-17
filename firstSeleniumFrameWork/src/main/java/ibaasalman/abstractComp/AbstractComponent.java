package ibaasalman.abstractComp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver Driver;
	WebDriverWait wait; 
	
	public AbstractComponent(WebDriver Driver){
		this.Driver = Driver;
		wait = new WebDriverWait(Driver,Duration.ofSeconds(5));
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(css="#toast-container")
	WebElement toastMsgElement ;
	
	@FindBy(className = "ng-animating")
	WebElement loadingIcon ;
	
	public void waitUntillToastVisible(){
		//wait for toast massage to be visible
		wait.until(ExpectedConditions.visibilityOf(toastMsgElement));
	}
	
	public void waitUntilLoadingInvisible() {
		//wait for loading to be invisible
		wait.until(ExpectedConditions.invisibilityOf(loadingIcon));
	}
			
}
