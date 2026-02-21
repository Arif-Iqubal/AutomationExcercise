package utils;

import java.io.FileInputStream;
import java.util.Properties;
//When i load the file in single method getProperty() then that that time each time when i call getProperty() each time file is loaded that create lock because each time i dont close the file after read and due to this properties file behave weirdly because reading operation is performed from help of file inputStream
/*Right now your code loads the file every time you call getProperty() and it also keeps the file open (you never close FileInputStream). After a few reads, Windows can lock the file / stream and then properties may behave weirdly.

Also: your path is hard-coded, so if you run from another machine, it will fail.*/
//solved the problem in below code
public class ConfigReader {

    private static Properties p;

    static {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
             //"D:\VS Code Workspace\coding\java\AutomationExercise_Ecommerce\src\test\resources\config.properties"
            p = new Properties();
            p.load(fis);
            fis.close();//this is important to prevent from lock
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties file not loaded");
        }
    }

    public static String getProperty(String key) {
        return p.getProperty(key);
    }
}
