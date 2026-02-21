package com.automationexercise.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.utils.ExcelUtils;
import com.automationexercise.utils.ExtentTestListener;

@Listeners(ExtentTestListener.class)
public class SignupTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(SignupTest.class);

    String filePath = "src/test/resources/LoginSignupData_Fresh.xlsx";

    @DataProvider(name = "signupData")
    public Object[][] getSignupData() {
        log.info("Loading signup test data from Excel");
        ExcelUtils excel = new ExcelUtils(filePath, "SignupData");
        return excel.getSheetData();
    }

    @Test(dataProvider = "signupData")
    public void signupTest(String tcId,
                           String firstName,
                           String lastName,
                           String email,
                           String password,
                           String day,
                           String month,
                           String year,
                           String company,
                           String address,
                           String country,
                           String state,
                           String city,
                           String zipcode,
                           String mobile) {

        log.info("Starting Signup Test: " + tcId);

        SignupPage signup = new SignupPage(driver);

        signup.openSignupPage();

        // First step: name + email
        signup.enterSignupDetails(firstName, email);

        // Check duplicate email case
        if (signup.isErrorMessageDisplayed()) {
            String errorMsg = signup.getErrorMessage();
            log.warn("Duplicate email error displayed: " + errorMsg);

            Assert.assertEquals(errorMsg,
                    "Email Address already exist!",
                    "Error message should indicate duplicate email");

            log.info("Negative signup test passed for: " + tcId);
            return;
        }

        // Account information (positive case)
        signup.fillAccountInformation(password, day, month, year);

        // Address information
        signup.fillAddressInformation(firstName, lastName, company,
                address, state, city, zipcode, mobile);

        signup.clickCreateAccount();

        log.info("Signup completed successfully for: " + tcId);
    }
}
