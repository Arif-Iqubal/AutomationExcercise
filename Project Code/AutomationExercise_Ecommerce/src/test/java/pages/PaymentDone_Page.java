package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class PaymentDone_Page {
	
	WebDriver driver;
	public PaymentDone_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	 @FindBy(xpath="//h2[normalize-space()='Order Placed!']") WebElement orderPlacedMessage;
	 @FindBy(css="a[data-qa='continue-button']") WebElement continueButton;
	 
	 public boolean checkOrderPlaced()
	 {
		 return orderPlacedMessage.isDisplayed();
	 }
	 public void continueButton()
	 {
		 WaitUtils.waitforClickable(driver, continueButton).click();
	 }

}
