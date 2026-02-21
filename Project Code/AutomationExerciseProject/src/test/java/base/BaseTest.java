package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.ITestResult;

import utils.ExtentManager;
import utils.ScreenshotUtil;

import com.aventstack.extentreports.*;

import java.time.Duration;
import java.lang.reflect.Method;
import org.openqa.selenium.JavascriptExecutor;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setup(Method method) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelectorAll('iframe').forEach(e=>e.remove())");
        
        test = extent.createTest(method.getName());
    }

  
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.fail(result.getThrowable());

            try {
                test.addScreenCaptureFromPath(path);
            } catch (Exception e) {}
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }

        driver.quit();
    }
    
    @AfterSuite
    public void endReport() {
        extent.flush();
    }
}
