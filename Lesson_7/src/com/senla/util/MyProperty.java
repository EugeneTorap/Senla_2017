package com.senla.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyProperty {
    private static volatile Properties properties = null;
    private final static Logger LOGGER = Logger.getLogger(MyProperty.class);


    public static Properties getInstance() {
        if (properties == null) {
            synchronized (Properties.class){
                if (properties == null) {
                    properties = new Properties();
                    loadMyProperty();
                }
            }
        }
        return properties;
    }

    private static void loadMyProperty(){
        String path = "resources/app.properties";
        while (true) {
            try (FileInputStream in = new FileInputStream(path)) {
                properties.load(in);
            } catch (FileNotFoundException e) {
                LOGGER.error(e.getMessage());
                path = Input.nextLine("Input path: ");
                continue;
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                continue;
            }
            break;
        }
    }
}
