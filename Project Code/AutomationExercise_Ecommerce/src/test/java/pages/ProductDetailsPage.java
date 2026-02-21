package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class ProductDetailsPage {

    WebDriver driver;

    @FindBy(id="quantity")
    WebElement quantityBox;

    @FindBy(css="button.cart")
    WebElement addToCartBtn;

    @FindBy(xpath="//u[normalize-space()='View Cart']")
    WebElement viewCart;

    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setQuantity(int qty){
        WaitUtils.waitforVisible(driver, quantityBox);
        quantityBox.clear();
        quantityBox.sendKeys(String.valueOf(qty));
    }

    public void clickAddToCart(){
        WaitUtils.waitforClickable(driver, addToCartBtn).click();
    }

    public void openCartFromPopup(){
        WaitUtils.waitforClickable(driver, viewCart).click();
    }
}
