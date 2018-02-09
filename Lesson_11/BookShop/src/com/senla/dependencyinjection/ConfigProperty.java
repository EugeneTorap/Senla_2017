package com.senla.dependencyinjection;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperty {
    private static Properties properties = null;
    private final static Logger LOGGER = Logger.getLogger(ConfigProperty.class);
    private final static String PATH = "resources/config.properties";

    public static Properties getInstance() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(new FileInputStream(PATH));
            } catch (IOException e) {
                LOGGER.error("Not found config.properties", e);
            }
        }
        return properties;
    }
}
