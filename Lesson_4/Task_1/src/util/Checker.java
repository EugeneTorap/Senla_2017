package util;

import entity.Book;
import entity.Order;
import entity.Reader;
import entity.Request;

public class Checker {

    public static int getPosition(Book[] books){
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null)
                return i;
        }
        return -1;
    }

    public static int getPosition(Reader[] readers){
        for (int i = 0; i < readers.length; i++) {
            if (readers[i] == null)
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
}
