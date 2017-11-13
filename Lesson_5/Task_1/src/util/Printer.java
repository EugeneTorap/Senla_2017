package util;

import entity.Book;
import entity.Order;

import java.util.List;

public class Printer {

    public static void print(Book book) {
        System.out.println(book.toString());
    }

    public static void print(Order order) {
        System.out.println(order.toString());
    }

    public static void print(int number) {
        System.out.println(number);
    }

    public static void printBooks(List<Book> books){
        System.out.println();
        for (Book book : books) {
            print(book);
        }
    }

    public static void printOrders(List<Order> orders){
        System.out.println();
        for (Order order : orders) {
            print(order);
        }
    }
}
