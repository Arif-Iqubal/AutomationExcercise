package com.automationexercise.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.ExcelUtils;
import com.automationexercise.utils.ExtentTestListener;

@Listeners(ExtentTestListener.class)
public class LoginTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(LoginTest.class);

    String filePath = "src/test/resources/LoginSignupData_Fresh.xlsx";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        log.info("Loading login test data from Excel");
        ExcelUtils excel = new ExcelUtils(filePath, "LoginData");
        return excel.getSheetData();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String tcId,
                          String email,
                          String password) {

        log.info("Starting Login Test: " + tcId);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginLink();
        loginPage.login(email, password);

        boolean errorDisplayed = loginPage.isLoginErrorDisplayed();

        if (errorDisplayed) {
            log.info(tcId + " → Negative login handled");
        } else {
            log.info(tcId + " → Login successful");
        }

        log.info("Completed Login Test: " + tcId);
    }
}
