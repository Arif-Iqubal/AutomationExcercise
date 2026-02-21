package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
	
	public static void handleAdOverlayIfPresent(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	    By overlay = By.cssSelector(".ad-overlay, .modal-backdrop, .popup-overlay");
	    By closeBtn = By.cssSelector(".close, .btn-close, .popup-close, .close-btn");

	    try {
	        // If overlay is present
	        if (driver.findElements(overlay).size() > 0) {

	            // Try clicking close button
	            if (driver.findElements(closeBtn).size() > 0) {
	                wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
	            }

	            // Wait until overlay disappears
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
	        }
	    } catch (Exception e) {
	        System.out.println("Overlay handling skipped: " + e.getMessage());
	    }
	}

	/*✅wait for Presence of element= DOM me aa gaya
✅ wait for Visible of element = screen pe dikh raha hai*/
	
	public static WebElement waitforVisible(WebDriver driver,WebElement ele)
	{
		handleAdOverlayIfPresent(driver);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ad-overlay")));
		return wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static WebElement waitforPresence(WebDriver driver,By lc)
	{
		handleAdOverlayIfPresent(driver);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(lc));
	}
	public static WebElement waitforClickable(WebDriver driver,WebElement ele)
	{
		handleAdOverlayIfPresent(driver);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public static boolean waitforInvisibiltyofElement(WebDriver driver,By lc)
	{
		handleAdOverlayIfPresent(driver);
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    return wait.until(ExpectedConditions.invisibilityOfElementLocated(lc));
		    //This means: wait until product row becomes invisible.this is helpful for UI to get the wait time for updation of cart product data.
		    
	}
	public static void waitForSeconds(int sec) {
	    try {
	        Thread.sleep(sec * 1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}



}
