package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage {

    WebDriver driver;

    public ContactPage(WebDriver driver){
        this.driver = driver;
    }

    By name = By.name("name");
    By email = By.name("email");
    By message = By.name("message");

    public boolean isContactFormVisible(){
        return driver.findElement(name).isDisplayed()
            && driver.findElement(email).isDisplayed()
            && driver.findElement(message).isDisplayed();
    }
}
