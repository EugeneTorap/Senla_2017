package com.senla.dao;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JDBCProperty {
    private static Properties properties = null;
    private final static Logger LOGGER = Logger.getLogger(JDBCProperty.class);
    private final static String PATH = "resources/jdbc.properties";

    public static Properties getInstance() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(new FileInputStream(PATH));
            } catch (IOException e) {
                LOGGER.error("Not found jdbc.properties", e);
            }
        }
        return properties;
    }
}
