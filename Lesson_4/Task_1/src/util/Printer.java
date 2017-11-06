package util;

import entity.Book;
import entity.Order;

public class Printer {
    public static void print(String string) {
        System.out.println(string);
    }

    public static void print(Book book) {
        if (book != null) {
            System.out.println(book.toString());
        }
    }

    public static void print(Order order) {
        if (order != null) {
            System.out.println(order.toString());
        }
    }

    public static void print(int number) {
        System.out.println(number);
    }

    public static void printArray(Book[] books){
        System.out.println();
        for (Book book : books) {
            print(book);
        }
    }

    public static void printArray(Order[] orders){
        System.out.println();
        for (Order order : orders) {
            print(order);
        }
    }
}
