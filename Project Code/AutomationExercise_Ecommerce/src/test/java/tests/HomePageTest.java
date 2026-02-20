package tests;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import utils.ConfigReader;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyLoginButtonClickable()throws Exception {
    	WebDriver driver=getDriver();
String urlString=ConfigReader.getProperty("homeurl");
    	driver.get(urlString);
    	driver.manage().window().maximize();
    	
    	 JavascriptExecutor js=(JavascriptExecutor)driver;
 	    js.executeScript("document.querySelectorAll('iframe').forEach(e=>e.remove())");
 	    
        HomePage home = new HomePage(driver);
        home.clickLogin();
        

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), "Login page not opened!");
    }

   

   @Test
   public void verifyCartButtonClickable()throws Exception{
	   WebDriver driver=getDriver();
	   String urlString=ConfigReader.getProperty("homeurl");
   	driver.get(urlString);
   	driver.manage().window().maximize();
       HomePage home = new HomePage(getDriver());
       
       home.clickCart();
      String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("view_cart"), "Cart page not opened!");
   }
}
