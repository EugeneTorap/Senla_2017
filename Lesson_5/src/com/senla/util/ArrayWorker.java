package com.senla.util;

import com.senla.entity.Book;
import com.senla.entity.Order;
import com.senla.entity.Reader;
import com.senla.entity.Request;

import java.util.List;

public class ArrayWorker {

    public static Book searchBook(List<Book> books, int id){
        for (Book book : books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    public static Reader searchReader(List<Reader> readers, int id){
        for (Reader reader : readers) {
            if (id == reader.getId()) {
                return reader;
            }
        }
        return null;
    }

    public static int searchOrder(List<Order> orders, int id){
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i) != null && orders.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public static Request searchRequest(List<Request> requests, int id){
        for (Request request : requests) {
            if (request != null && request.getId() == id)
                return request;
        }
        return null;
    }
}
