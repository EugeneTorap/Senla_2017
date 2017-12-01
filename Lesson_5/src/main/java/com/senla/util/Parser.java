package com.senla.util;

import com.senla.entity.*;
import com.senla.enums.Status;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final static Logger LOGGER = Logger.getLogger(Parser.class);
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static List<Book> parseBook(String[] strings) {
        List<Book> books = new ArrayList<>();
        for (String string: strings) {
            String[] str = string.split(" ");
            try {
                Book book = new Book(str[0], Integer.parseInt(str[2]),df.parse(str[4]), df.parse(str[5]));
                book.setId(Integer.parseInt(str[1]));
                book.setTheBookInStore(Boolean.parseBoolean(str[3]));
                books.add(book);
            } catch (ParseException e) {
                LOGGER.error("ParseDate");
            }
        }
        return books;
    }

    public static List<Reader> parseReader(String[] strings) {
        List<Reader> readers = new ArrayList<>();
        for(String string: strings){
            String[] str = string.split(" ");
            Reader reader = new Reader(str[1]);
            reader.setId(Integer.parseInt(str[0]));
            readers.add(reader);
        }
        return readers;
    }

    public static List<Order> parseOrder(String[] strings, List<Book> loadedBooks) {
        List<Order> orders = new ArrayList<>();
        for(String string: strings){
            String[] str = string.split(" ");
            List<Book> books = new ArrayList<>();
            for (int j = 0; j < Integer.parseInt(str[5]); j++) {
                books.add(ArrayWorker.search(loadedBooks, Integer.parseInt(str[6 + j])));
            }
            try {
                Order order = new Order(new Reader(str[0]), df.parse(str[4]), books);
                order.setId(Integer.parseInt(str[1]));
                order.setStatus(Status.valueOf(str[2]));
                orders.add(order);
            } catch (ParseException e) {
                LOGGER.error("ParseDate");
            }
        }
        return orders;
    }

    public static List<Request> parseRequest(String[] strings, List<Book> loadedBooks, List<Reader> loadedReader) {
        List<Request> requests = new ArrayList<>();
        for(String string: strings){
            String[] str = string.split(" ");
            Book book = (ArrayWorker.search(loadedBooks, Integer.parseInt(str[2])));
            Reader reader = (ArrayWorker.search(loadedReader, Integer.parseInt(str[1])));
            Request request = new Request(book, reader);
            request.setId(Integer.parseInt(str[0]));
            requests.add(request);
        }
        return requests;
    }
}
