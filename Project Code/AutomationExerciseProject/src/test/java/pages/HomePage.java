package pages;
import org.openqa.selenium.By;
import utils.ActionUtils;
import org.openqa.selenium.WebDriver;
public class HomePage {

	ActionUtils action;
	private WebDriver driver;
	public HomePage(WebDriver driver){
	    this.driver = driver;
	    action = new ActionUtils(driver);
	}
    // Top Menu
    By productsMenu = By.xpath("//a[contains(text(),'Products')]");
    By cartMenu = By.xpath("//a[contains(text(),'Cart')]");
    By loginMenu = By.xpath("//a[contains(text(),'Signup / Login')]");
    By testCaseMenu = By.xpath("//a[contains(text(),'Test Cases')]");
    By apiMenu = By.xpath("//a[contains(text(),'API Testing')]");
    By contactMenu = By.xpath("//a[contains(text(),'Contact us')]");

    By categorySidebar = By.xpath("//h2[text()='Category']");
    By womenCategory = By.xpath("//a[@href='#Women']");
    By womenDress = By.xpath("//a[@href='/category_products/1']");

    By menCategory = By.xpath("//a[@href='#Men']");
    By menTshirt = By.xpath("//a[@href='/category_products/3']");

    By kidsCategory = By.xpath("//a[@href='#Kids']");
    By kidsDress = By.xpath("//a[@href='/category_products/4']");


    // Footer subscription
    By emailBox = By.id("susbscribe_email");
    By subscribeBtn = By.id("subscribe");

    public void clickProducts() { action.jsClick(productsMenu); }
    public void clickCart() { action.jsClick(cartMenu); }
    public void clickLogin() { action.jsClick(loginMenu); }
    public void clickTestCases() { action.jsClick(testCaseMenu); }
    public void clickAPI() { action.jsClick(apiMenu); }
    public void clickContact() { action.jsClick(contactMenu); }

    public void clickWomenDress(){

        action.scrollToElement(categorySidebar);

        action.jsClick(womenCategory);      
        action.waitForVisible(womenDress); 
        action.jsClick(womenDress);
    }

    public void clickMenTshirts(){

        action.scrollToElement(categorySidebar);

        action.jsClick(menCategory);        
        action.waitForVisible(menTshirt);   
        action.jsClick(menTshirt);
    }

    public void clickKidsDress(){

        action.scrollToElement(categorySidebar);

        action.jsClick(kidsCategory);
        action.waitForVisible(kidsDress);
        action.jsClick(kidsDress);
    }

    public void subscribeEmail(String email){

        action.scrollToElement(emailBox);   // scroll to footer
   
        driver.findElement(emailBox).clear();
        driver.findElement(emailBox).sendKeys(email);

        action.jsClick(subscribeBtn);       // JS click avoids ad iframe
    }

}  
