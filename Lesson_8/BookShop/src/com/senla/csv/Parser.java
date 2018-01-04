package com.senla.csv;

import com.senla.annotations.CsvEntity;
import com.senla.annotations.CsvProperty;
import com.senla.enums.PropertyType;
import com.senla.model.entity.Book;
import com.senla.model.entity.*;

import com.senla.enums.Status;
import com.senla.util.ArrayWorker;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final static Logger LOGGER = Logger.getLogger(Parser.class);
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static List<? extends Entity> parse(Class<? extends Entity> clazz, List<String> strings) {
        List<Entity> entities = new ArrayList<>();
        try {
            String separator = clazz.getAnnotation(CsvEntity.class).valuesSeparator();
            for (String string : strings) {
                String[] str = string.split(separator);
                Entity entity = clazz.getConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(CsvProperty.class)) {
                        CsvProperty annotation = field.getDeclaredAnnotation(CsvProperty.class);
                        int num = annotation.columnNumber();
                        field.setAccessible(true);
                        if (annotation.propertyType() == PropertyType.SimpleProperty) {
                            switch (field.getType().getSimpleName()) {
                                case "String":
                                    field.set(entity, str[num - 1]);
                                    break;
                                case "int":
                                    field.set(entity, Integer.parseInt(str[num - 1]));
                                    break;
                                case "Boolean":
                                    field.set(entity, Boolean.parseBoolean(str[num - 1]));
                                    break;
                                case "Date":
                                    field.set(entity, df.parse(str[num - 1]));
                                    break;
                            }
                        }
                    }
                }
                entities.add(entity);
            }
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return entities;
    }
}
