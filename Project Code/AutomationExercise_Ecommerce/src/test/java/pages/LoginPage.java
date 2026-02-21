package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import utils.WaitUtils;
//Page should only contain element and actions
public class LoginPage {
	  @FindBy(xpath="//input[@data-qa='login-email']") WebElement Username;

	    @FindBy(xpath="//input[@data-qa='login-password']") WebElement pasword;
	    
	    @FindBy(xpath="//button[@data-qa='login-button']") WebElement loginclick;
	    private WebDriver driver;
	    public  LoginPage(WebDriver driver){
	    this.driver=driver;
	    	
		        PageFactory.initElements(driver, this);
		}
	    
	    public  void login(String username,String password)
	    {
	    	WaitUtils.waitforVisible(driver,Username).sendKeys(username);
	    	WaitUtils.waitforVisible(driver,pasword).sendKeys(password);
	    	WaitUtils.waitforClickable(driver,loginclick).click();
	    
	   }

}
