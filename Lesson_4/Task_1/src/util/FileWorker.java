package util;

import com.danco.training.TextFileWorker;
import entity.Book;
import entity.Order;
import entity.Request;
import enums.Status;

import java.util.GregorianCalendar;

public class FileWorker {
    private static TextFileWorker textFileWorker;
    private String bookPath;
    private String orderPath;
    private String requestPath;
    private int counter = 0;

    public FileWorker(String bookPath, String requestPath, String orderPath) {
        this.bookPath = bookPath;
        this.requestPath = requestPath;
        this.orderPath = orderPath;
    }

    public void saveToFile(Book[] books) {
        textFileWorker = new TextFileWorker(this.bookPath);

        for (Book book : books) {
            if (book != null) {
                counter++;
            }
        }
        String[] strings = new String[counter];
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                strings[i] = books[i].toString();
            }
        }
        textFileWorker.writeToFile(strings);
    }

    public void saveToFile(Order[] orders) {
        textFileWorker = new TextFileWorker(this.orderPath);
        for (Order order : orders) {
            if (order != null) {
                counter++;
            }
        }
        String[] strings = new String[counter];
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                strings[i] = orders[i].toString();
            }

        }
        textFileWorker.writeToFile(strings);
    }

    public void saveToFile(Request[] requests) {
        textFileWorker = new TextFileWorker(this.requestPath);
        for (Request request : requests) {
            if (request != null) {
                counter++;
            }
        }
        String[] strings = new String[counter];
        for (int i = 0; i < requests.length; i++) {
            if (requests[i] != null) {
                strings[i] = requests[i].toString();
            }
        }
        textFileWorker.writeToFile(strings);
    }

    public Book[] loadTheBooks() {
        textFileWorker = new TextFileWorker(this.bookPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Book[] books = new Book[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] bookString = strings[i].split(" ");
                books[i].setTitle(bookString[0]);
                books[i].setId(Integer.parseInt(bookString[1]));
                books[i].setPrice(Integer.parseInt(bookString[2]));
                books[i].setTheBookInStore(Boolean.parseBoolean(bookString[3]));
                GregorianCalendar pubDate = new GregorianCalendar(Integer.parseInt(bookString[4]),
                        Integer.parseInt(bookString[5]), Integer.parseInt(bookString[6]));
                books[i].setDatePublished(pubDate);
            }
            return books;
        }
        return null;
    }

    public Order[] loadTheOrders() {
        textFileWorker = new TextFileWorker(this.orderPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Order[] orders = new Order[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] orderString = strings[i].split(" ");
                orders[i].getReader().setName(orderString[0]);
                orders[i].setId(Integer.parseInt(orderString[1]));
                orders[i].setStatus(Status.valueOf(orderString[2]));
            }
            return orders;
        }
        return null;
    }

    public Request[] loadTheRequests() {
        textFileWorker = new TextFileWorker(this.requestPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Request[] requests = new Request[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] requestString = strings[i].split(" ");
                requests[i].getReader().setName(requestString[0]);
                requests[i].getBook().setTitle(requestString[1]);
                requests[i].setAmount(Integer.parseInt(requestString[2]));
            }
            return requests;
        }
        return null;
    }
}
