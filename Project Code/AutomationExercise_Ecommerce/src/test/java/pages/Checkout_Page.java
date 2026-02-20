package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class Checkout_Page {
	
	WebDriver driver;
	public  Checkout_Page(WebDriver driver) {
		 
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath="//a[normalize-space()='Place Order']") WebElement placeOrder;
	@FindBy(id="address_delivery")
	private WebElement addressDetails;


	
	public void placeOrder()
	{
		WaitUtils.waitforClickable(driver, placeOrder).click();
	}
	
	//This will check the first line of address
	public boolean isAddressVisible() {
	    try {
	    	WaitUtils.waitforVisible(driver, addressDetails);
	        return addressDetails.isDisplayed()&& !addressDetails.getText().isEmpty();  // true if visible
	    } catch (NoSuchElementException e) {
	        return false; // element not found
	    }
	}

	}


