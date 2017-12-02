package com.senla.util;

import com.senla.model.entity.Entity;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
    private final static Logger LOGGER = Logger.getLogger(FileWorker.class);


    public static void save(List<? extends Entity> entities, String path){
        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), false))) {
                for (Entity entity : entities) {
                    bw.write(entity.toString() + "\n");
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }
    }

    public static String[] load(String path) {
        List<String> strings = new ArrayList<>();
        while (true) {
            String str;
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                while ((str = reader.readLine()) != null) {
                    strings.add(str);
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }
        return strings.toArray(new String[0]);
    }
}

