package util;

import com.danco.training.TextFileWorker;
import entity.Book;
import entity.Order;
import entity.Reader;
import entity.Request;
import enums.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
    private static TextFileWorker textFileWorker;
    private String bookPath;
    private String orderPath;
    private String readerPath;
    private String requestPath;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public FileWorker(String bookPath, String readerPath, String orderPath, String requestPath) {
        this.bookPath = bookPath;
        this.readerPath = readerPath;
        this.orderPath = orderPath;
        this.requestPath = requestPath;
    }

    public void saveBooks(List<Book> books) {
        textFileWorker = new TextFileWorker(this.bookPath);
        List<String> strings = new ArrayList<>();

        for (Book book : books) {
            strings.add(book.toString());
        }
        textFileWorker.writeToFile(strings.toArray(new String[0]));
    }

    public void saveOrders(List<Order> orders) {
        textFileWorker = new TextFileWorker(this.orderPath);
        List<String> strings = new ArrayList<>();

        for (Order order : orders) {
            strings.add(order.toString());
        }
        textFileWorker.writeToFile(strings.toArray(new String[0]));
    }

    public void saveReaders(List<Reader> readers) {
        textFileWorker = new TextFileWorker(this.readerPath);
        List<String> strings = new ArrayList<>();

        for (Reader reader : readers) {
            strings.add(reader.toString());
        }
        textFileWorker.writeToFile(strings.toArray(new String[0]));
    }

    public void saveRequests(List<Request> requests) {
        textFileWorker = new TextFileWorker(this.requestPath);
        List<String> strings = new ArrayList<>();

        for (Request request : requests) {
            strings.add(request.toString());
        }
        textFileWorker.writeToFile(strings.toArray(new String[0]));
    }

    public List<Book> loadBooks() throws ParseException {
        textFileWorker = new TextFileWorker(this.bookPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Book> books = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");
                books.add(i, new Book(str[0], Integer.parseInt(str[2]), df.parse(str[4]), df.parse(str[5])));
                books.get(i).setId(Integer.parseInt(str[1]));
                books.get(i).setTheBookInStore(Boolean.parseBoolean(str[3]));
            }
            return books;
        }
        return null;
    }

    public List<Reader> loadReader() {
        textFileWorker = new TextFileWorker(this.readerPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Reader> readers = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");
                readers.add(i, new Reader(str[1]));
                readers.get(i).setId(Integer.parseInt(str[0]));
            }
            return readers;
        }
        return null;
    }

    public List<Order> loadOrders(List<Book> loadedBooks) throws ParseException {
        textFileWorker = new TextFileWorker(this.orderPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");

                List<Book> books = new ArrayList<>();
                for (int j = 0; j < Integer.parseInt(str[5]); j++) {
                    books.add(ArrayWorker.searchBook(loadedBooks, Integer.parseInt(str[6 + j])));
                }
                orders.add(i, new Order(new Reader(str[0]), df.parse(str[4]), books));
                orders.get(i).setId(Integer.parseInt(str[1]));
                orders.get(i).setStatus(Status.valueOf(str[2]));
            }
            return orders;
        }
        return null;
    }

    public List<Request> loadRequests(List<Book> loadedBooks, List<Reader> loadedReader) {
        textFileWorker = new TextFileWorker(this.requestPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            List<Request> requests = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");
                Book book = (ArrayWorker.searchBook(loadedBooks, Integer.parseInt(str[2])));
                Reader reader = (ArrayWorker.searchReader(loadedReader, Integer.parseInt(str[1])));
                requests.add(i, new Request(book, reader));
                requests.get(i).setId(Integer.parseInt(str[0]));
            }
            return requests;
        }
        return null;
    }
}
