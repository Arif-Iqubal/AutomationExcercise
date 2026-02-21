package base;




import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;



public class BaseTest {
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();

	@BeforeMethod
	public void setup()throws Exception
	{
		
		//getDriver().manage().deleteAllCookies();
		
	

		WebDriver d=new ChromeDriver();
		driver.set(d);
	    
	   
	    
		
		
	}
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	
	
	@AfterMethod
	public void tearup()
	{
		getDriver().quit();
	}
}
