package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddToCart;
import utils.ConfigReader;

public class AddToCartTest extends BaseTest {
	
//	@Test
//	public void verify_addToCart()
//	{
//		String urlString=ConfigReader.getProperty("product_pageUrl");
//		getDriver().get(urlString);
//	    	getDriver().manage().window().maximize();
//	    	
//	    	//Add Removal
//	    	 JavascriptExecutor js=(JavascriptExecutor)getDriver();
//	 	    js.executeScript("document.querySelectorAll('iframe').forEach(e=>e.remove())");
//	 	    
//		ProductPage pp=new ProductPage(getDriver());
//		
//		Assert.assertTrue(pp.addToCart().getText().trim().equalsIgnoreCase("Blue Top"),"Cart Add is Unsuccessful");
//	 	    
//	 	 }
	
	@Test
	public void addMultipleProducts() {

	    getDriver().get(ConfigReader.getProperty("product_pageUrl"));
	    getDriver().manage().window().maximize();
	    JavascriptExecutor js=(JavascriptExecutor)getDriver();
 	    js.executeScript("document.querySelectorAll('iframe').forEach(e=>e.remove())");
 	    

	    AddToCart ac = new AddToCart(getDriver());

	    //Adding product to cart using id
//	    ac.addProductByHover(1);
//	    ac.addProductByHover(7);
//	    ac.addProductByHover(3);
	    
	    //Adding product to cart using productName
	    ac.addProductByHover("Blue Top");
	    ac.addProductByHover("Grunt Blue Slim Fit Jeans");
	    ac.addProductByHover("Sleeveless Dress");

	    ac.openCart();

	    Assert.assertTrue(ac.getCartProductsCount() == 3);
	}


}
