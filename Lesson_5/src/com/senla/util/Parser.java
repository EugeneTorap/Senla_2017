package com.senla.util;

import com.senla.entity.*;
import com.senla.enums.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static Book parseBook(String string) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] str = string.split(" ");
        Book book = null;
        try {
            book = new Book(str[0], Integer.parseInt(str[2]),df.parse(str[4]), df.parse(str[5]));
        } catch (ParseException e) {
            System.out.println("Date not parsed, util/Parser/parseBook");
        }
        book.setId(Integer.parseInt(str[1]));
        book.setTheBookInStore(Boolean.parseBoolean(str[3]));
        return book;
    }

    public static Reader parseReader(String string) {
        String[] str = string.split(" ");
        Reader reader = new Reader(str[1]);
        reader.setId(Integer.parseInt(str[0]));
        return reader;
    }

    public static Order parseOrder(String string, List<Book> loadedBooks) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] str = string.split(" ");
        List<Book> books = new ArrayList<>();
        for (int j = 0; j < Integer.parseInt(str[5]); j++) {
            books.add(ArrayWorker.searchBook(loadedBooks, Integer.parseInt(str[6 + j])));
        }
        Order order = null;
        try {
            order = new Order(new Reader(str[0]), df.parse(str[4]), books);
        } catch (ParseException e) {
            System.out.println("Date not parsed, util/Parser/parseOrder");
        }
        order.setId(Integer.parseInt(str[1]));
        order.setStatus(Status.valueOf(str[2]));
        return order;
    }

    public static Request parseRequest(String string, List<Book> loadedBooks, List<Reader> loadedReader) {
        String[] str = string.split(" ");
        Book book = (ArrayWorker.searchBook(loadedBooks, Integer.parseInt(str[2])));
        Reader reader = (ArrayWorker.searchReader(loadedReader, Integer.parseInt(str[1])));
        Request request = new Request(book, reader);
        request.setId(Integer.parseInt(str[0]));
        return request;
    }
}
