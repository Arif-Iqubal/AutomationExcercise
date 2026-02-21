package tests;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddToCart;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.SearchData;

public class ProductPageTest extends BaseTest {

    private void removeIframes(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelectorAll('iframe').forEach(e=>e.remove())");
    }

    private void addProductsToCart(String tcId) {
        AddToCart ac = new AddToCart(getDriver());

        List<String> products = ExcelUtils.getProductsToAdd(tcId);

        for (String product : products) {
            ac.addProductByHover(product);
        }

        ac.openCart();
    }

    @Test(priority = 1)
    public void PROD_001_searchProductWithValidKeyword() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("product_pageUrl"));
        driver.manage().window().maximize();
        removeIframes(driver);

        SearchData data = ExcelUtils.getSearchData("PROD_001");

        ProductsPage products = new ProductsPage(getDriver());
        products.searchProduct(data.getKeyword());

        Assert.assertTrue(products.isSearchedProductsHeadingDisplayed(),
                "Searched Products heading not displayed!");

        Assert.assertTrue(products.getSearchedProductsCount() >= data.getExpectedMinResults(),
                "No products displayed for valid search keyword!");
    }

    @Test(priority = 2)
    public void PROD_002_searchProductWithInvalidValidKeyword() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("product_pageUrl"));
        driver.manage().window().maximize();
        removeIframes(driver);

        SearchData data = ExcelUtils.getSearchData("PROD_002");

        ProductsPage products = new ProductsPage(getDriver());
        products.searchProduct(data.getKeyword());

        Assert.assertTrue(products.isSearchedProductsHeadingDisplayed(),
                "Searched Products heading not displayed!");

        Assert.assertTrue(products.getSearchedProductsCount() <= data.getExpectedMinResults(),
                "Products displayed for invalid keyword!");
    }

    @Test(priority = 3)
    public void PROD_003_addMultipleProductToCart() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("product_pageUrl"));
        driver.manage().window().maximize();
        removeIframes(driver);

        addProductsToCart("PROD_003");

        AddToCart ac = new AddToCart(driver);
        int expectedCount = ExcelUtils.getProductsToAdd("PROD_003").size();
        Assert.assertEquals(ac.getCartProductsCount(), expectedCount);

    }

   @Test(priority = 4)
    public void PROD_004_removeProductFromCart() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("product_pageUrl"));
        driver.manage().window().maximize();
        removeIframes(driver);

        // Add products
        addProductsToCart("PROD_004");

        CartPage cart = new CartPage(driver);

        // Remove products from Excel
        List<String> removeList = ExcelUtils.getProductsToRemove("PROD_004");

        for (String product : removeList) {
            cart.removeProductByName(product);
        }

        AddToCart ac = new AddToCart(driver);
        Assert.assertEquals(ac.getCartProductsCount(), 1);
    }

   @Test(priority = 5)
    public void PROD_005_addProductDetailsPage() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("product_pageUrl"));
        driver.manage().window().maximize();
        removeIframes(driver);

        // Get product name from Excel
        List<String> products = ExcelUtils.getProductsToAdd("PROD_005");
        String productName = products.get(0);

        int qty = ExcelUtils.getQuantityForProduct("PROD_005", productName);

        ProductsPage pp = new ProductsPage(driver);
        pp.clickViewProduct(productName);

        ProductDetailsPage pd = new ProductDetailsPage(driver);
        pd.setQuantity(qty);
        pd.clickAddToCart();
        pd.openCartFromPopup();

        CartPage cart = new CartPage(driver);
        int actualQty = cart.getQuantityForProduct(productName);

        Assert.assertEquals(actualQty, qty);
    }

    @Test(priority = 6)
    public void PROD_006_verify_Cart_Persist_After_Refresh() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("product_pageUrl"));
        driver.manage().window().maximize();
        removeIframes(driver);

        // Add products from Excel
        addProductsToCart("PROD_006");
        
       driver.navigate().back();

        // From Excel, pick the first product for details
        List<String> products = ExcelUtils.getProductsToAdd("PROD_006");
        String productName = products.get(0);

        int qty = ExcelUtils.getQuantityForProduct("PROD_006", productName);

        // open product details
        ProductsPage pp = new ProductsPage(driver);
        pp.clickViewProduct(productName);

        // set qty and add to cart
        ProductDetailsPage pd = new ProductDetailsPage(driver);
        pd.setQuantity(qty);
        pd.clickAddToCart();
        pd.openCartFromPopup();

        CartPage cart = new CartPage(driver);

        // Before refresh
        Assert.assertEquals(cart.getTotalItemsInCart(), 3);
        Assert.assertEquals(cart.getQuantityForProduct(productName), qty+1);

        driver.navigate().refresh();

        // After refresh
        Assert.assertEquals(cart.getTotalItemsInCart(), 3);
        Assert.assertEquals(cart.getQuantityForProduct(productName), qty+1);
    }
}
