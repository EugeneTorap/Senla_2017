package com.senla.dependencyinjection;

import com.senla.util.Input;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperty {
    private static Properties properties = null;
    private final static Logger LOGGER = Logger.getLogger(ConfigProperty.class);


    public static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
            loadMyProperty();
        }
        return properties;
    }

    private static void loadMyProperty(){
        String path = "resources/config.properties";
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
