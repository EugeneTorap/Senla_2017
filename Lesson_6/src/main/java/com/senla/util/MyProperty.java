package com.senla.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyProperty {
    private static volatile Properties properties = null;


    public static Properties getInstance() {
        if (properties == null) {
            synchronized (Properties.class){
                if (properties == null) {
                    properties = new Properties();
                }
            }
        }
        return properties;
    }

    public static String getMyProperty(String str){
        try(FileInputStream in = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return properties.getProperty(str);
    }

}
