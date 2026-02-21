package com.automationexercise.utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private static final Logger log =
            LogManager.getLogger(WaitUtils.class);

    public static WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        try {
            log.info("Waiting for element to be visible");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            log.error("Element not visible within timeout", e);
            throw e;
        }
    }

    public static WebElement waitForElementClickable(WebDriver driver, WebElement element) {
        try {
            log.info("Waiting for element to be clickable");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.error("Element not clickable within timeout", e);
            throw e;
        }
    }
}
