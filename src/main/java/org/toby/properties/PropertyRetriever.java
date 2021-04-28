package org.toby.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyRetriever {

    private static final String PROPERTIESFILEPATH = "src/main/resources/resources.properties";
    private static Properties properties;

    static{
        readPropertiesFile();
    }

    private PropertyRetriever() {
    }

    private static void readPropertiesFile() {
        try (FileInputStream fis = new FileInputStream(PROPERTIESFILEPATH)) {
            properties = new Properties();
            properties.load(fis);
        }  catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}