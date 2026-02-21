package com.automationexercise.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    private static final Logger log =
            LogManager.getLogger(ExtentManager.class);

    public static ExtentReports getInstance() {

        if (extent == null) {

            log.info("Initializing Extent Report");

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setReportName("Automation Exercise Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            log.info("Extent Report created at: test-output/ExtentReport.html");
        }

        return extent;
    }
}
