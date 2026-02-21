package tests;
import base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(priority = 1)
    public void testTopMenuNavigation() {
        HomePage home = new HomePage(driver);

        home.clickProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("products"));

        driver.navigate().back();
        home.clickCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"));

        driver.navigate().back();
        home.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test(priority = 2)
    public void testCategoryNavigation() {

        driver.get("https://automationexercise.com/");
        HomePage home = new HomePage(driver);
        ProductsPage product = new ProductsPage(driver);

        home.clickWomenDress();
        Assert.assertTrue(product.isCategoryPageLoaded("WOMEN"));

        driver.get("https://automationexercise.com/");
        home.clickMenTshirts();
        Assert.assertTrue(product.isCategoryPageLoaded("MEN"));

        driver.get("https://automationexercise.com/");
        home.clickKidsDress();
        Assert.assertTrue(product.isCategoryPageLoaded("KIDS"));
    }


    @Test(priority = 3)
    public void testAddToCartFlow() {
        HomePage home = new HomePage(driver);
        ProductsPage product = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);

        home.clickProducts();
        product.addFirstProductToCart();
        product.clickViewCart();

        Assert.assertTrue(cart.isCartDisplayed());
    }

    @Test(priority = 4)
    public void testContactAndFooter() {
        HomePage home = new HomePage(driver);
        ContactPage contact = new ContactPage(driver);

        home.clickContact();
        Assert.assertTrue(contact.isContactFormVisible());

        driver.navigate().back();
        home.subscribeEmail("test@gmail.com");
    }
}
