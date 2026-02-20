package tests;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddToCart;
import pages.CartPage;
import pages.Checkout_Page;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentDone_Page;
import pages.Payment_Page;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.PaymentData;

public class CheckoutTest extends BaseTest {

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
    public void CHK_001_checkoutAfterLogin() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("login_url"));
        driver.manage().window().maximize();
        removeIframes(driver);

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // Products
        HomePage hp = new HomePage(driver);
        hp.productBtn();

        // Add products from Excel
        addProductsToCart("CHK_001");

        // Checkout
        CartPage cart = new CartPage(driver);
        cart.proceedCheckout();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://automationexercise.com/checkout",
                "No checkout happened");
    }

    @Test(priority = 2)
    public void CHK_002_checkoutWithoutLogin() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("homeurl"));
        driver.manage().window().maximize();
        removeIframes(driver);

        // Products
        HomePage hp = new HomePage(driver);
        hp.productBtn();

        // Add products from Excel
        addProductsToCart("CHK_002");

        // Checkout
        CartPage cart = new CartPage(driver);
        cart.proceedCheckout();

        String msg = cart.getCheckoutModalMessage();

        Assert.assertTrue(msg.contains("Register / Login"),
                "Modal message not correct. Actual: " + msg);

        Assert.assertTrue(cart.isRegisterLoginButtonDisplayed(),
                "Register/Login button not visible!");
    }

    @Test(priority = 3, dependsOnMethods = "CHK_001_checkoutAfterLogin")
    public void CHK_003_paymentWithValidCard() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("login_url"));
        driver.manage().window().maximize();
        removeIframes(driver);

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // Products
        HomePage hp = new HomePage(driver);
        hp.productBtn();

        // Add products from Excel
        addProductsToCart("CHK_003");

        // Checkout
        CartPage cart = new CartPage(driver);
        cart.proceedCheckout();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://automationexercise.com/checkout",
                "No checkout happened");

        // Place Order
        Checkout_Page cp = new Checkout_Page(driver);
        cp.placeOrder();

        // Payment (Excel Driven)
        PaymentData payData = ExcelUtils.getPaymentData("CHK_003");

        Payment_Page payment = new Payment_Page(driver);
        payment.paymentCompletion(payData);

        // Verify
        PaymentDone_Page verify = new PaymentDone_Page(driver);
        Assert.assertTrue(verify.checkOrderPlaced(), "No order is placed");
    }

    @Test(priority = 4, dependsOnMethods = "CHK_001_checkoutAfterLogin")
    public void CHK_004_verifyAddressDetailVisible() {

        WebDriver driver = getDriver();
        driver.get(ConfigReader.getProperty("login_url"));
        driver.manage().window().maximize();
        removeIframes(driver);

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // Products
        HomePage hp = new HomePage(driver);
        hp.productBtn();

        // Add products from Excel
        addProductsToCart("CHK_004");

        // Checkout
        CartPage cart = new CartPage(driver);
        cart.proceedCheckout();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://automationexercise.com/checkout",
                "No checkout happened");

        // Verify Address
        Checkout_Page cp = new Checkout_Page(driver);
        Assert.assertTrue(cp.isAddressVisible(), "No address is visible");
    }
}
