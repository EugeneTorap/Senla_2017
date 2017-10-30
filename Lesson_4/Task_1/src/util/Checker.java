package util;

import entity.Book;
import entity.Order;
import entity.Request;

public class Checker {

    public static int getPosition(Book[] books){
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null)
                return i;
        }
        return -1;
    }

    public static int getPosition(Order[] orders){
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null)
                return i;
        }
        return -1;
    }

    public static int getPosition(Request[] requests){
        for (int i = 0; i < requests.length; i++) {
            if (requests[i] == null)
                return i;
        }
        return -1;
    }

    public static Book search(Book[] books, int id){
        for (Book book : books) {
            if (id == book.getId()) {
                return book;
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

}
