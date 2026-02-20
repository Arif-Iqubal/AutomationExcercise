package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SafeClick {
	/*✅ AutomationExercise “Add to cart” needs a real user click

On this site, sometimes the Add to cart button works only when the click comes from:

Actions.click() (real mouse click)

not from JavascriptExecutor click()

Because JS click does not always trigger hover + overlay + event listeners properly.

So yes: SafeClick can click, but item won’t be added.*/

    public static void s_click(WebDriver driver, WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", ele);
        js.executeScript("arguments[0].click();", ele);
    }
    
}
