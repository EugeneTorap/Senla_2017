package util;

import entity.Book;
import entity.Order;
import entity.Request;

public class Printer {
    public static void print(String string) {
        System.out.println(string);
    }

    public static void print(Book book) {
        if (book != null) {
            System.out.println(book.getTitle() + ", price: " + book.getPrice() +
                    ", id: " + book.getId() + ", date: " + book.getDatePublished());
        }
    }

    public static void print(int number) {
        System.out.println(number);
    }

    public static void printArray(Book[] books){
        System.out.println();
        for (Book book : books) {
            if (book != null) {
                System.out.println(book.getTitle() + ", price: " + book.getPrice() +
                        ", id: " + book.getId() + ", date: " + book.getDatePublished());
            }
        }
    }

    public static void printArray(Order[] orders){
        for (Order order : orders) {
            if (order != null) {
                System.out.println(order.getId());
            }
        }
    }

    public static void printArray(Request[] requests){
        for (Request request : requests) {
            if (request != null) {
                System.out.println(request.getReader().getName() + ", book: " + request.getBook().getTitle() +
                ", amount: " + request.getAmount());
            }
        }
    }
}
