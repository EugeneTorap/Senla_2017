package com.senla.csv;

import com.senla.annotations.*;
import com.senla.controller.repositories.*;
import com.senla.model.entity.*;
import com.senla.model.entity.Reader;
import com.senla.util.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CSVWorker {
    private final static Logger LOGGER = Logger.getLogger(CSVWorker.class);


    public static void save(List<? extends Entity> entities) {
        CsvEntity csvEntity = entities.get(0).getClass().getAnnotation(CsvEntity.class);
        String path = csvEntity.filename();
        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
                Method method = entities.get(0).getClass().getMethod("toStringContents");
                bw.write((String)method.invoke(entities.get(0)));
                for (Entity entity : entities) {
                    bw.write(entity + "\n");
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
                List<Book> parsedBooks = Parser.parseBook(lines, csvEntity.valuesSeparator());
                List<Book> myBooks = BookRepository.getInstance().getBooks();
                setEntity((List<Entity>)(List<?>)parsedBooks, (List<Entity>)(List<?>)myBooks);
                break;
            case "Order":
                List<Order> parsedOrders = Parser.parseOrder(lines, BookRepository.getInstance().getBooks(),
                        csvEntity.valuesSeparator());
                List<Order> myOrders = OrderRepository.getInstance().getOrders();
                setEntity((List<Entity>)(List<?>)parsedOrders, (List<Entity>)(List<?>)myOrders);
                break;
            case "Reader":
                List<Reader> parsedReaders = Parser.parseReader(lines, csvEntity.valuesSeparator());
                List<Reader> myReaders = ReaderRepository.getInstance().getReaders();
                setEntity((List<Entity>)(List<?>)parsedReaders, (List<Entity>)(List<?>)myReaders);
                break;
            case "Request":
                List<Request> parsedRequests = Parser.parseRequest(lines, BookRepository.getInstance().getBooks(),
                        ReaderRepository.getInstance().getReaders(), csvEntity.valuesSeparator());
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
