package com.automationexercise.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automationexercise.utils.ConfigReader;
import com.automationexercise.utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    protected static final Logger log =
            LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {

        log.info("Reading browser from config");
        String browser = ConfigReader.getProperty("browser");

        log.info("Initializing browser: " + browser);
        driver = DriverFactory.initDriver(browser);

        String url = ConfigReader.getProperty("url");
        log.info("Opening URL: " + url);

        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {

        log.info("Closing browser");

        if (driver != null) {
            driver.quit();
        }
    }
}
