package utils;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    static {
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            System.out.println("Config file not found");
        }
    }

    public static String getURL() {
        return prop.getProperty("url");
    }

    public static String getBrowser() {
        return prop.getProperty("browser");
    }
}
