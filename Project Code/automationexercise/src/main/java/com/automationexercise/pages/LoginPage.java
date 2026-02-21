package com.automationexercise.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationexercise.utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;

    // Logger
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("LoginPage initialized");
    }

    // ---------- Locators ----------

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLink;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    private WebElement loginErrorMessage;

    // ---------- Actions ----------

    public void clickLoginLink() {
        log.info("Clicking on Login link");
        WaitUtils.waitForElementClickable(driver, loginLink).click();
    }

    public void enterEmail(String email) {
        log.info("Entering email: " + email);
        WaitUtils.waitForElementVisible(driver, emailField).clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        log.info("Entering password");
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        log.info("Clicking Login button");
        WaitUtils.waitForElementClickable(driver, loginButton).click();
    }

    public void login(String email, String password) {
        log.info("Performing login action");
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    // ---------- Validation ----------

    public boolean isLoginErrorDisplayed() {
        try {
            log.info("Checking for login error message");
            WaitUtils.waitForElementVisible(driver, loginErrorMessage);
            return loginErrorMessage.isDisplayed();
        } catch (Exception e) {
            log.info("No login error message displayed");
            return false;
        }
    }

    public String getLoginErrorMessage() {
        log.info("Fetching login error message text");
        return loginErrorMessage.getText();
    }
}
