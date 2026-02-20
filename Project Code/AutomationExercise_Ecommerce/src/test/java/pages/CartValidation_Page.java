package pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;
import utils.WaitUtils;

public class CartValidation_Page {
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']") WebElement continueShopping;
	 @FindBy(css="a[href='/view_cart']")  WebElement navigate_ViewCart;
	 WebDriver driver;
	 public CartValidation_Page(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver,this);
		
	}
	 
	 public Set<String> addMultipleProducts(int count) {

		    Set<String> expectedNames = new HashSet<>();

		    for (int i = 1; i <= count; i++) {

		    	  WebElement productBox = driver.findElement(
		    		        By.xpath("(//div[@class='product-image-wrapper'])[" + i + "]")//this is the full parent element of product it means each product has parent element like div and in that parent div child div are also present.
		    		    );
		    //	  WebElement productName = productBox.findElement(By.cssSelector("p"));//this element overlays thats why it is not required here and it is taking time which create problem for the next view cart 

		    		    WebElement addToCartBtn = driver.findElement(
		    		        By.xpath("(//div[@class='product-image-wrapper'])[" + i + "]//a[contains(@class,'add-to-cart')]")
		    		    );

		    		    // Scroll product into view
		    		    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", productBox);
		    		    
		    		    //getting only name of selected product
		    		    WebElement productName = productBox.findElement(By.cssSelector(".productinfo p"));//getting the locator from parent element productBox instead of driver
		    		    String name = productName.getText().trim();
		    		    expectedNames.add(name);


//		        String name = productBox.getText().trim();
//		        expectedNames.add(name);

		    
		        Actions act = new Actions(driver);
		        act.moveToElement(productBox).perform();
		        act.moveToElement(addToCartBtn).click().perform();

		        if (i != count) {
		            WaitUtils.waitforClickable(driver, continueShopping).click();
		        } else {
		            WaitUtils.waitforClickable(driver, navigate_ViewCart).click();
		        }
		    }

		    return expectedNames;
		}

	//Cart Products
	public Set<String> getAllCartProductNames() {

	    List<String> names = new ArrayList<>();

	    List<WebElement> cartNameElements =
	            driver.findElements(By.cssSelector("td.cart_description h4 a"));//through this locator getting all the product WebElements present in cart

	    for (WebElement ele : cartNameElements) {
	        names.add(ele.getText().trim());
	    }

	    return new HashSet<>(names);//transffering list to set
	}



}
