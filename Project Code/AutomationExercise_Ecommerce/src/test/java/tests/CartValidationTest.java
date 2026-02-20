package tests;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartValidation_Page;
import utils.ConfigReader;

public class CartValidationTest extends BaseTest {
	
	@Test
	public void verifyCartValidation()
	{
		WebDriver driver=getDriver();
		   driver.get(ConfigReader.getProperty("product_pageUrl"));
		    driver.manage().window().maximize();
		    JavascriptExecutor js=(JavascriptExecutor)driver;
	 	    js.executeScript("document.querySelectorAll('iframe').forEach(e=>e.remove())");
	 	    

		CartValidation_Page cp=new CartValidation_Page(driver);
		Set<String>selectedProductNames=cp.addMultipleProducts(5);
		Set<String> cartProductNamesSet=cp.getAllCartProductNames();
		selectedProductNames.forEach(System.out::println);
		System.out.println();
		cartProductNamesSet.forEach(System.out::println);
		
	//	Assert.assertTrue(cartProductNamesSet.containsAll(selectedProductNames),"Unsuccessful Product Validation");
		
		//or
		Assert.assertEquals(cartProductNamesSet, selectedProductNames,"Cart products are not matching!");
		/*✅ What TestNG does internally
		 * When you write:Assert.assertEquals(cartProductNamesSet, selectedProductNames);
		 * TestNG calls:cartProductNamesSet.equals(selectedProductNames)
		 * And Set.equals() checks:size must be same,all elements must be same
		 *  When it will FAIL->It will fail if:any product is missing any extra product is added,name mismatch (spaces, different case, etc.)
		 *  Example:selectedProductNames = [Blue Top, Men Tshirt]
		 *              cartProductNamesSet = [Blue Top]
		 *              ❌ FAIL (size mismatch)*/
		
		
		
	}

}
