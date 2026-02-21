package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String path = "test-output/screenshots/" + testName + ".png";
            File dest = new File(path);

            FileUtils.copyFile(src, dest);
            return path;

        } catch (Exception e) {
            return null;
        }
    }
}
