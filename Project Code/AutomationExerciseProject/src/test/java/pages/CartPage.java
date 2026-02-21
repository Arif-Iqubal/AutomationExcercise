package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    By cartPageText = By.xpath("//li[contains(text(),'Shopping Cart')]");

    public boolean isCartDisplayed(){
        return driver.findElement(cartPageText).isDisplayed();
    }
}
