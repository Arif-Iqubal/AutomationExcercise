package com.automationexercise.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    private static final Logger log =
            LogManager.getLogger(DriverFactory.class);

    public static WebDriver initDriver(String browser) {

        WebDriver driver = null;

        log.info("Initializing browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            log.info("Launching Chrome browser");
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            log.info("Launching Edge browser");
            driver = new EdgeDriver();

        } else {
            log.error("Unsupported browser: " + browser);
            throw new RuntimeException("Browser not supported");
        }

        log.info("Maximizing browser window");
        driver.manage().window().maximize();

        return driver;
    }
}
