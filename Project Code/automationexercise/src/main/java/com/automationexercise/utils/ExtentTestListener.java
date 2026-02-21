package com.automationexercise.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static final Logger log =
            LogManager.getLogger(ExtentTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.info("Starting test: " + testName);

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.info("Test passed: " + testName);

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.error("Test failed: " + testName, result.getThrowable());

        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.warn("Test skipped: " + testName);

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Flushing Extent Report");
        extent.flush();
    }
}
