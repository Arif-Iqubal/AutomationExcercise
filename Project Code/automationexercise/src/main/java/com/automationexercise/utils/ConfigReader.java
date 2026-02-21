package com.automationexercise.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

    private static Properties prop;
    private static final Logger log =
            LogManager.getLogger(ConfigReader.class);

    static {
        try {
            log.info("Loading configuration file");
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config.properties");

            prop = new Properties();
            prop.load(fis);

            log.info("Configuration file loaded successfully");

        } catch (IOException e) {
            log.error("Failed to load configuration file", e);
        }
    }

    public static String getProperty(String key) {
        String value = prop.getProperty(key);
        log.info("Reading property: " + key + " = " + value);
        return value;
    }
}
