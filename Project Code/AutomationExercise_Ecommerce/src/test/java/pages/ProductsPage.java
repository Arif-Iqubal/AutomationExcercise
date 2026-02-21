package pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class ProductsPage {

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search_product")
    private WebElement searchBox;
    
 
   
    
//   Or if multiple products:
//@FindBy(css = "a[href^='/product_details/']")
//List<WebElement> viewProductBtns;
//Then click the first one:viewProductBtns.get(0).click();


    @FindBy(id = "submit_search")
    private WebElement searchBtn;

    @FindBy(css = ".features_items .product-image-wrapper")
    private List<WebElement> searchedProducts;
    /*div.product-image-wrapper it contain all product as well as their count that which products appared or which products are on that section*/

    @FindBy(css = "h2.title.text-center")//heading of the section in which all products are present
    private WebElement searchedProductsHeading;//checking that heading is displayed or not
    /*Products Page Reloaded
  - Search input (value = "Top")
  - Search button
  - Heading: "SEARCHED PRODUCTS"
  - ONLY filtered products list
🎯 Why we verify heading?*/

    public void searchProduct(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBtn.click();
    }

    public boolean isSearchedProductsHeadingDisplayed() {
    	return WaitUtils.waitforVisible(driver, searchedProductsHeading).isDisplayed();

       
    }

    public int getSearchedProductsCount() {//whatever type of product is searched we will get count of it
        return searchedProducts.size();
    }
    
    public void clickViewProduct(String productName) {

    	By viewProductLocator = By.xpath(
    	        "//p[normalize-space()='" + productName + "']" +
    	        "/ancestor::div[@class='product-image-wrapper']" +
    	        "//a[contains(@href,'/product_details')]"
    	    );
        WebElement viewProduct = WaitUtils.waitforPresence(driver, viewProductLocator);
        ((JavascriptExecutor)driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", viewProduct);

        WaitUtils.waitforClickable(driver, viewProduct).click();
    }


}
