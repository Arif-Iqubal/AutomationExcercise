package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;
import utils.ConfigReader;
import utils.WaitUtils;

public class HomePage {

    @FindBy(css="a[href='/login']")
    WebElement loginBtn;

    @FindBy(css="a[href='/view_cart']")
    WebElement cartBtn;
    
    @FindBy(css="a[href='/logout']")
    WebElement logoutBtn;
    
    @FindBy(css="a[href='/products']")
    WebElement prdBtn;
    
   

 //   WebDriver driver;
private WebDriver driver;
    public HomePage(WebDriver driver){
    	this.driver=driver;
    	
        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        WaitUtils.waitforClickable(driver, loginBtn).click();
    }

    public void clickCart() {
        WaitUtils.waitforClickable(driver, cartBtn).click();
    }
    public void productBtn() {
        WaitUtils.waitforClickable(driver, prdBtn).click();
    }
    public void clicklogOut()
    {
    	WaitUtils.waitforClickable(driver, logoutBtn).click();
    }
}
