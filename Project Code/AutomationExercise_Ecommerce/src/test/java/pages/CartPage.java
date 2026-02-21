package pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;
import utils.WaitUtils;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[normalize-space()='Proceed To Checkout']")WebElement checkout;
	

	By clickHere = By.linkText("here");//Without login this link exist
	
	//Modal/Popup appear after clicking proceed to checkout without login
    // Modal container
    @FindBy(css = "div.modal-content")
    WebElement checkoutModal;

    // Modal message text
    @FindBy(css = "div.modal-body p")
    WebElement modalMessage;

    // Register/Login button inside modal
    @FindBy(css = "div.modal-body a[href='/login']")
    WebElement registerLoginBtn;

    // Close modal button (X)
    @FindBy(css = "button.close-modal")
    WebElement closeBtn;




	public  void proceedCheckout()
	{
		WaitUtils.waitforClickable(driver, checkout).click();
		
	}
	
	public void clickHere() {
		WaitUtils.waitforClickable(driver,driver.findElement(clickHere)).click();;
	}

	public void removeProductByName(String productName) {

	    WebElement deleteBtn = driver.findElement(
	        By.xpath("//a[normalize-space()='" + productName + "']" +
	                 "/ancestor::tr//a[contains(@class,'cart_quantity_delete')]")
	    );

	    WaitUtils.waitforClickable(driver, deleteBtn).click();
	    
	    By productRow = By.xpath("//tr[.//a[text()='" + productName + "']]");
	    WaitUtils.waitforInvisibiltyofElement(driver, productRow);
    
	    

	}
	public int getQuantityForProduct(String productName) {

	    String qtyXpath =
	        "//td[@class='cart_description']//a[normalize-space()='" + productName + "']" +
	        "/ancestor::tr//td[@class='cart_quantity']//button";

	    WebElement qtyBtn = WaitUtils.waitforVisible(driver,driver.findElement(By.xpath(qtyXpath)));
	    return Integer.parseInt(qtyBtn.getText().trim());
	}
	
	public int getTotalItemsInCart() //total different type of products
	{
	    List<WebElement> rows = driver.findElements(By.cssSelector("tr[id^='product-']"));
	    return rows.size();
	}
	
	//Checkout Modal Actions
	 
	    public String getCheckoutModalMessage() {
	        WaitUtils.waitforVisible(driver, checkoutModal);
	        return modalMessage.getText().trim();
	    }

	    public boolean isRegisterLoginButtonDisplayed() {
	        WaitUtils.waitforVisible(driver, checkoutModal);
	        return registerLoginBtn.isDisplayed();
	    }

	    public void clickRegisterLoginFromModal() {
	        WaitUtils.waitforClickable(driver, registerLoginBtn).click();
	    }

	    public void closeModal() {
	        WaitUtils.waitforClickable(driver, closeBtn).click();
	    }
	

	
	

}
