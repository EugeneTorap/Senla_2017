package com.senla.di;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DIProperty {
    private static Properties properties = null;
    private final static Logger LOGGER = Logger.getLogger(DIProperty.class);
    private final static String PATH = "resources/di.properties";

    public static Properties getInstance() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(new FileInputStream(PATH));
            } catch (IOException e) {
                LOGGER.error("Not found di.properties", e);
            }
        }
        return properties;
    }
}
