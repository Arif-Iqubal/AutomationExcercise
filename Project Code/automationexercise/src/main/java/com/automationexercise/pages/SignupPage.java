package com.automationexercise.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automationexercise.utils.WaitUtils;

public class SignupPage {

    private WebDriver driver;

    // Logger
    private static final Logger log =
            LogManager.getLogger(SignupPage.class);

    // Constructor
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("SignupPage initialized");
    }

    // ---------- Step 1 Locators ----------
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLink;

    @FindBy(name = "name")
    private WebElement signupName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmail;

    @FindBy(xpath = "//button[text()='Signup']")
    private WebElement signupButton;

    // ---------- Error Message ----------
    @FindBy(xpath = "//p[normalize-space()='Email Address already exist!']")
    private WebElement errorMessage;

    // ---------- Account Information ----------
    @FindBy(id = "id_gender1")
    private WebElement titleMr;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "days")
    private WebElement day;

    @FindBy(id = "months")
    private WebElement month;

    @FindBy(id = "years")
    private WebElement year;

    @FindBy(id = "newsletter")
    private WebElement newsletter;

    @FindBy(id = "optin")
    private WebElement offers;

    // ---------- Address Information ----------
    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "address1")
    private WebElement address;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "zipcode")
    private WebElement zipcode;

    @FindBy(id = "mobile_number")
    private WebElement mobile;

    @FindBy(xpath = "//button[text()='Create Account']")
    private WebElement createAccountBtn;

    // ---------- Actions ----------

    public void openSignupPage() {
        log.info("Opening signup page");
        loginLink.click();
    }

    public void enterSignupDetails(String name, String email) {

        log.info("Entering signup name: " + name);
        WaitUtils.waitForElementVisible(driver, signupName).sendKeys(name);

        log.info("Entering signup email: " + email);
        signupEmail.sendKeys(email);

        log.info("Scrolling to Signup button");

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", signupButton);

        try {
            log.info("Clicking Signup button normally");
            WaitUtils.waitForElementClickable(driver, signupButton).click();
        } catch (Exception e) {
            log.warn("Normal click failed, using JavaScript click");

            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", signupButton);
        }
    }

    public void fillAccountInformation(String pwd, String d, String m, String y) {
        log.info("Filling account information");

        titleMr.click();
        password.sendKeys(pwd);

        log.info("Selecting DOB: " + d + " " + m + " " + y);
        new Select(day).selectByVisibleText(d);
        new Select(month).selectByVisibleText(m);
        new Select(year).selectByVisibleText(y);

        newsletter.click();
        offers.click();
    }

    public void fillAddressInformation(String fName, String lName, String comp,
                                       String addr, String st, String cty,
                                       String zip, String mob) {

        log.info("Filling address information");

        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        company.sendKeys(comp);
        address.sendKeys(addr);

        log.info("Selecting country: India");
        new Select(country).selectByVisibleText("India");

        state.sendKeys(st);
        city.sendKeys(cty);
        zipcode.sendKeys(zip);
        mobile.sendKeys(mob);
    }

    public void clickCreateAccount() {

        log.info("Scrolling to Create Account button");

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", createAccountBtn);

        try {
            log.info("Trying normal click");
            WaitUtils.waitForElementClickable(driver, createAccountBtn).click();
        } catch (Exception e) {
            log.warn("Normal click failed, using JavaScript click");

            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", createAccountBtn);
        }
    }


    // ---------- Validation ----------

    public boolean isErrorMessageDisplayed() {
        try {
            log.info("Checking for duplicate email error message");
            WaitUtils.waitForElementVisible(driver, errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            log.info("No duplicate email error displayed");
            return false;
        }
    }

    public String getErrorMessage() {
        log.info("Fetching error message text");
        return errorMessage.getText();
    }
}
