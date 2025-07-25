package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    public static String getBaseUrl() {
        return props.getProperty("base.url");
    }
}
