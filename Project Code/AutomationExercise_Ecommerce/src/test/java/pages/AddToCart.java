package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class AddToCart {
	
	//Typically, an overlay that appears on hover would have initial styles that hide it, such as opacity: 0, visibility: hidden, or display: none. These properties would then change to make it visible when the parent element (or the overlay itself) is hovered.
	@FindBy(xpath="(//div[@class='product-image-wrapper'])[1]") WebElement firstProduct;
	@FindBy(xpath="(//a[contains(@class,'add-to-cart') and contains(.,'Add to cart')])[1]") WebElement firstProduct_Cart;
	@FindBy(css="td.cart_description h4 a") WebElement selectedProduct_cart;
	

	WebDriver driver;
	Actions actions;
	 public AddToCart(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        
	        actions = new Actions(driver);
	    }

		
	//For adding more than one product using productName
	 @FindBy(xpath = "//button[normalize-space()='Continue Shopping']")
	    WebElement continueShopping;

//	    @FindBy(xpath = "//u[normalize-space()='View Cart']")
//	    WebElement viewCart;//modal/popup view cart button this will may fail 
	 @FindBy(xpath="//a[@href='/view_cart']")
	 WebElement viewCart;


	    @FindBy(css = "td.cart_description h4 a")
	    List<WebElement> cartProducts;

	   
	    // 🔥 Main method
	    public void addProductByHover(String productName) {

	        WebElement product = driver.findElement(
	                By.xpath("//div[@class='productinfo text-center']/p[text()='" + productName + "']/ancestor::div[@class='product-image-wrapper']")
	        );

	        WebElement addToCartBtn = product.findElement(
	                By.xpath(".//a[contains(@class,'add-to-cart') and contains(.,'Add to cart')]")
	        );
	     // scroll product into view
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({block:'center'});", product);
	     // hover
	        actions.moveToElement(product).perform();

	        // small wait for hover overlay to settle
	     //   WaitUtils.waitForSeconds(0); for extreme low Internet condition
	        
	        // click using Actions (best for hover elements)
	        actions.moveToElement(addToCartBtn).click().perform();
	        
	       //or
	      //  WaitUtils.waitforClickable(driver, addToCartBtn);
	        
	        // wait popup
	      WaitUtils.waitforVisible(driver, continueShopping);

	        // click Continue Shopping (safe click)
	        safeClick(continueShopping);

	        // wait popup gone
		      WaitUtils.waitforInvisibiltyofElement(driver,By.xpath("//button[normalize-space()='Continue Shopping']"));
	    }

	    public void openCart() {
	    	WaitUtils.waitforClickable(driver, viewCart);//this wait also work
	    	actions.moveToElement(viewCart).perform();
	        safeClick(viewCart);
	    }

	    public int getCartProductsCount() {
	        return cartProducts.size();
	    }

	    // ✅ Safe click (normal click + JS fallback)
	    private void safeClick(WebElement element) {
	        try {
	            WaitUtils.waitforClickable(driver, element).click();
	        } catch (Exception e) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].click();", element);
	        }
	    }

	    
	  //For adding single product
	//	
//		public WebElement addToCart() {
	//
//		    // scroll to product
//		    ((org.openqa.selenium.JavascriptExecutor) driver)
//		            .executeScript("arguments[0].scrollIntoView(true);", firstProduct);
	//
//		    Actions act = new Actions(driver);
//		    act.moveToElement(firstProduct).perform();
	//
//		    
//		    // Click Add to cart (not product)
//		    act.moveToElement(firstProduct_Cart).click().perform();
//		 //   WaitUtils.waitforClickable(driver, firstProduct_Cart).click();
	//
//		    // click view cart from popup
//		   WaitUtils.waitforClickable(driver, viewCart).click();
//		  
//		 //   SafeClick.s_click(driver, viewCart);
	//
//		    return selectedProduct_cart;
//		}
		
		//For adding more than one product using id
//		 @FindBy(css="a[href='/view_cart']")  WebElement navigate_ViewCart;
//		public void addProductByHover(int index) {
	//
//		    WebElement productBox = driver.findElement(
//		        By.xpath("(//div[@class='product-image-wrapper'])[" + index + "]")
//		    );
	//
//		    WebElement addToCartBtn = driver.findElement(
//		        By.xpath("(//div[@class='product-image-wrapper'])[" + index + "]//a[contains(@class,'add-to-cart')]")
//		    );
	//
//		    // Scroll product into view
//		    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", productBox);
//		    
	//
//		    Actions act = new Actions(driver);
	//
//		    // Hover
//		    act.moveToElement(productBox).perform();
	//
//		    // Click Add to cart (REAL click)
//		    WaitUtils.waitforClickable(driver, addToCartBtn);
//		    act.moveToElement(addToCartBtn).click().perform();
	//
//		    // Modal -> Continue Shopping
//		    WaitUtils.waitforClickable(driver, continueShopping).click();
//		}
	//	

	}
