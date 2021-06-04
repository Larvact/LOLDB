package org.toby.properties;

import java.io.BufferedReader;
import java.io.FileReader;
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
        try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTIESFILEPATH))) {
            properties = new Properties();
            properties.load(reader);
        }  catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}