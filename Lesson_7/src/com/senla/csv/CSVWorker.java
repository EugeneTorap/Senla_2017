package com.senla.csv;

import com.senla.annotations.CsvEntity;
import com.senla.controller.repositories.*;
import com.senla.model.entity.*;
import com.senla.model.entity.Reader;
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

        String[] lines = strings.toArray(new String[0]);
        int index;
        switch (clazz.getSimpleName()){
            case "Book":
                List<Book> books = Parser.parseBook(lines, csvEntity.valuesSeparator());
                for (Book book : books){
                    if ((index = ArrayWorker.searchIndex(books, book.getId())) != -1){
                        books.set(index, book);
                    } else {
                        books.add(book);
                    }
                }
                BookRepository.getInstance().setBooks(books);
                break;
            case "Order":
                List<Order> orders = Parser.parseOrder(lines, BookRepository.getInstance().getBooks(),
                        csvEntity.valuesSeparator());
                for (Order order : orders){
                    if ((index = ArrayWorker.searchIndex(orders, order.getId())) != -1){
                        orders.set(index, order);
                    } else {
                        orders.add(order);
                    }
                }
                OrderRepository.getInstance().setOrders(orders);
                break;
            case "Reader":
                List<Reader> readers = Parser.parseReader(lines, csvEntity.valuesSeparator());
                for (Reader reader : readers){
                    if ((index = ArrayWorker.searchIndex(readers, reader.getId())) != -1){
                        readers.set(index, reader);
                    } else {
                        readers.add(reader);
                    }
                }
                ReaderRepository.getInstance().setReaders(readers);
                break;
            case "Request":
                List<Request> requests = Parser.parseRequest(lines, BookRepository.getInstance().getBooks(),
                        ReaderRepository.getInstance().getReaders(), csvEntity.valuesSeparator());
                for (Request request : requests){
                    if ((index = ArrayWorker.searchIndex(requests, request.getId())) != -1){
                        requests.set(index, request);
                    } else {
                        requests.add(request);
                    }
                }
                RequestRepository.getInstance().setRequests(requests);
                break;
        }
    }
}
