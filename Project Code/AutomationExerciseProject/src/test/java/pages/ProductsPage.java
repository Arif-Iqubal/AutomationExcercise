package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
public class ProductsPage {

    WebDriver driver;
    ActionUtils action;
    public ProductsPage(WebDriver driver){
        this.driver = driver;
        action = new ActionUtils(driver);
    }

    By firstAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    By viewCartBtn = By.xpath("//u[contains(text(),'View Cart')]");

    public void addFirstProductToCart(){
        action.jsClick(firstAddToCart);
    }

    public void clickViewCart(){
        action.jsClick(viewCartBtn);
    }
    
    By categoryTitle = By.xpath("//h2[@class='title text-center']");
    public boolean isCategoryPageLoaded(String categoryName){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTitle));

        String title = driver.findElement(categoryTitle).getText();
        return title.toUpperCase().contains(categoryName);
    }

}
