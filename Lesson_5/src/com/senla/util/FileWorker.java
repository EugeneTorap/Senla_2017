package com.senla.util;

import com.danco.training.TextFileWorker;
import com.senla.entity.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
    private static TextFileWorker textFileWorker;

    public void save(List<? extends Entity> entities, String path) {
        List<String> strings = new ArrayList<>();

        for (Entity entity : entities) {
            strings.add(entity.toString());
        }
        new TextFileWorker(path).writeToFile(strings.toArray(new String[0]));
    }

    public List<Book> loadBooks(String path) {
        while (true){
            try {
                textFileWorker = new TextFileWorker(path);
            }catch (IllegalArgumentException e){
                System.out.println("File not found, manager/BookManager/loadBooks");
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }

        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Book> books = new ArrayList<>();
            for (String str : strings) {
                books.add(Parser.parseBook(str));
            }
            return books;
        }
        return null;
    }

    public List<Reader> loadReader(String path) {
        while (true){
            try {
                textFileWorker = new TextFileWorker(path);
            }catch (IllegalArgumentException e){
                System.out.println("File not found, manager/BookManager/loadReader");
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }

        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Reader> readers = new ArrayList<>();
            for (String str : strings) {
                readers.add(Parser.parseReader(str));
            }
            return readers;
        }
        return null;
    }

    public List<Order> loadOrders(String path, List<Book> loadedBooks) {
        while (true){
            try {
                textFileWorker = new TextFileWorker(path);
            }catch (IllegalArgumentException e){
                System.out.println("File not found, manager/BookManager/loadOrders");
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }

        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Order> orders = new ArrayList<>();
            for (String str : strings) {
                orders.add(Parser.parseOrder(str, loadedBooks));
            }
            return orders;
        }
        return null;
    }

    public List<Request> loadRequests(String path, List<Book> loadedBooks, List<Reader> loadedReader) {
        while (true){
            try {
                textFileWorker = new TextFileWorker(path);
            }catch (IllegalArgumentException e){
                System.out.println("File not found, manager/BookManager/loadRequests");
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }

        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Request> requests = new ArrayList<>();
            for (String str : strings) {
                requests.add(Parser.parseRequest(str, loadedBooks, loadedReader));
            }
            return requests;
        }
        return null;
    }
}
