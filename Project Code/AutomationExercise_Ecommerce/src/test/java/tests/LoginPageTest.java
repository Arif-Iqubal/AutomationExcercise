package tests;





import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void verifyLogin()throws Exception
	{
		
		String urlString=ConfigReader.getProperty("login_url");
	//	System.out.println(urlString);
	//	System.out.println(ConfigReader.getProperty("username"));
		WebDriver driver=getDriver();
    	driver.get(urlString);
    	driver.manage().window().maximize();
    	
    	 JavascriptExecutor js=(JavascriptExecutor)driver;
 	    js.executeScript("document.querySelectorAll('iframe').forEach(e=>e.remove())");
 	    
		LoginPage lp=new LoginPage(driver);
		lp.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
		Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/logout']")).isDisplayed(),"login is Unsuccessful");
	}

}
