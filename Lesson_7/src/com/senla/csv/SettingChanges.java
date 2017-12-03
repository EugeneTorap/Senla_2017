package com.senla.csv;

import com.senla.annotations.CsvEntity;
import com.senla.controller.repositories.*;
import com.senla.model.entity.*;
import com.senla.util.ArrayWorker;

import java.util.List;

public class SettingChanges {

    public static void setChanges(Class<? extends Entity> clazz, CsvEntity csvEntity, String[] lines){
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
