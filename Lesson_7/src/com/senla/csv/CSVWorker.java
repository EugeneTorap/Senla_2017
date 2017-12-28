package com.senla.csv;

import com.senla.annotations.*;
import com.senla.controller.repositories.*;
import com.senla.enums.PropertyType;
import com.senla.model.entity.*;
import com.senla.model.entity.Reader;
import com.senla.util.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVWorker {
    private final static Logger LOGGER = Logger.getLogger(CSVWorker.class);


    public static void save(List<? extends Entity> entities) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        CsvEntity csvEntity = entities.get(0).getClass().getAnnotation(CsvEntity.class);
        String path = csvEntity.filename();
        String separator = csvEntity.valuesSeparator();
        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
                Method method = entities.get(0).getClass().getMethod("toStringContents");
                bw.write((String)method.invoke(entities.get(0)));

                for (Entity entity : entities) {
                    StringBuilder str = new StringBuilder();
                    Field[] fields = entity.getClass().getDeclaredFields();
                    for (int j = 0; j < fields.length; j++) {
                        for (Field field : fields) {
                            if (field.isAnnotationPresent(CsvProperty.class)) {
                                CsvProperty annotation = field.getDeclaredAnnotation(CsvProperty.class);
                                int num = annotation.columnNumber();
                                field.setAccessible(true);
                                if (num == j + 1) {
                                    if (annotation.propertyType() == PropertyType.SimpleProperty) {
                                        if (field.getType().getSimpleName().equals("Date")) {
                                            str.append(df.format(field.get(entity))).append(separator);
                                        }
                                        str.append(field.get(entity)).append(separator);
                                    }
                                }
                            }
                        }
                    }
                    str.append("\n");
                    bw.write(str.toString());
                }

            } catch (NoSuchMethodException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                path = Input.nextLine("Input path: ");
                continue;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
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
                reader.readLine();
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
        setChanges(clazz, csvEntity, strings.toArray(new String[0]));
    }

    private static void setChanges(Class<? extends Entity> clazz, CsvEntity csvEntity, String[] lines){
        switch (clazz.getSimpleName()){
            case "Book":
                List<Book> parsedBooks = (List<Book>)(List<?>) Parser.parse(clazz, Arrays.asList(lines));
                List<Book> myBooks = BookRepository.getInstance().getBooks();
                setEntity((List<Entity>)(List<?>)parsedBooks, (List<Entity>)(List<?>)myBooks);
                break;
            case "Order":
                List<Order> parsedOrders = (List<Order>)(List<?>) Parser.parse(clazz, Arrays.asList(lines));
                List<Order> myOrders = OrderRepository.getInstance().getOrders();
                setEntity((List<Entity>)(List<?>)parsedOrders, (List<Entity>)(List<?>)myOrders);
                break;
            case "Reader":
                List<Reader> parsedReaders = (List<Reader>)(List<?>) Parser.parse(clazz, Arrays.asList(lines));
                List<Reader> myReaders = ReaderRepository.getInstance().getReaders();
                setEntity((List<Entity>)(List<?>)parsedReaders, (List<Entity>)(List<?>)myReaders);
                break;
            case "Request":
                List<Request> parsedRequests = (List<Request>)(List<?>) Parser.parse(clazz, Arrays.asList(lines));
                List<Request> myRequests = RequestRepository.getInstance().getRequests();
                setEntity((List<Entity>)(List<?>)parsedRequests, (List<Entity>)(List<?>)myRequests);
                break;
        }
    }

    private static void setEntity(List<Entity> entities, List<Entity> myEntities){
        int index;
        for (Entity entity : entities) {
            if ((index = ArrayWorker.searchIndex(myEntities, entity.getId())) != -1) {
                myEntities.set(index, entity);
            } else {
                myEntities.add(entity);
            }
        }
    }
}
