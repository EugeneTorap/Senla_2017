package util;

import entity.Book;
import entity.Order;
import entity.Reader;
import entity.Request;

public class ArrayWorker {

    public static Book search(Book[] books, int id){
        for (Book book : books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    public static Reader search(Reader[] readers, int id){
        for (Reader reader : readers) {
            if (id == reader.getId()) {
                return reader;
            }
        }
        return null;
    }

    public static int search(Order[] orders, int id){
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getId() == id)
                return i;
        }
        return -1;
    }

    public static Request search(Request[] requests, int id){
        for (Request request : requests) {
            if (request != null && request.getId() == id)
                return request;
        }
        return null;
    }

    public static Book[] extendArray(Book[] books) {
        Book[] newBooks = new Book[books.length * 2];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        return newBooks;
    }

    public static Reader[] extendArray(Reader[] readers) {
        Reader[] newReaders = new Reader[readers.length * 2];
        System.arraycopy(readers, 0, newReaders, 0, readers.length);
        return newReaders;
    }

    public static Order[] extendArray(Order[] orders) {
        Order[] newOrders = new Order[orders.length * 2];
        System.arraycopy(orders, 0, newOrders, 0, orders.length);
        return newOrders;
    }

    public static Request[] extendArray(Request[] requests) {
        Request[] newRequests = new Request[requests.length * 2];
        System.arraycopy(requests, 0, newRequests, 0, requests.length);
        return newRequests;
    }
}
