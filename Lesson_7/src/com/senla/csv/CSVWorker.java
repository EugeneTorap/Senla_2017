package com.senla.csv;

import com.senla.annotations.CsvEntity;
import com.senla.model.entity.Entity;
import com.senla.util.*;
import org.apache.log4j.Logger;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWorker {
    private final static Logger LOGGER = Logger.getLogger(CSVWorker.class);


    public static void save(List<? extends Entity> entities){
        CsvEntity csvEntity = entities.get(0).getClass().getAnnotation(CsvEntity.class);
        String path = csvEntity.filename();
        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
                for (Entity entity : entities) {
                    bw.write(entity + "\n");
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }
    }

    public static void load(Class<? extends Entity> clazz) {
        CsvEntity csvEntity = clazz.getAnnotation(CsvEntity.class);
        String path = csvEntity.filename();
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
        SettingChanges.setChanges(clazz, csvEntity, strings.toArray(new String[0]));

    }
}
