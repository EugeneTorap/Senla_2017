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

    public void saveToFile(Book[] books) {
        int count = 0;
        textFileWorker = new TextFileWorker(this.bookPath);

        for (Book book : books) {
            if (book != null) {
                count++;
            }
        }
        String[] strings = new String[count];
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                strings[i] = books[i].toString();
            }
        }
        textFileWorker.writeToFile(strings);
    }

    public void saveToFile(Order[] orders) {
        int count = 0;
        textFileWorker = new TextFileWorker(this.orderPath);
        for (Order order : orders) {
            if (order != null) {
                count++;
            }
        }
        String[] strings = new String[count];
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                strings[i] = orders[i].toString();
            }

        }
        textFileWorker.writeToFile(strings);
    }

    public void saveToFile(Reader[] readers) {
        int count = 0;
        textFileWorker = new TextFileWorker(this.readerPath);
        for (Reader reader : readers) {
            if (reader != null) {
                count++;
            }
        }
        String[] strings = new String[count];
        for (int i = 0; i < readers.length; i++) {
            if (readers[i] != null) {
                strings[i] = readers[i].toString();
            }
        }
        textFileWorker.writeToFile(strings);
    }

    public void saveToFile(Request[] requests) {
        int count = 0;
        textFileWorker = new TextFileWorker(this.requestPath);
        for (Request request : requests) {
            if (request != null) {
                count++;
            }
        }
        String[] strings = new String[count];
        for (int i = 0; i < requests.length; i++) {
            if (requests[i] != null) {
                strings[i] = requests[i].toString();
            }
        }
        textFileWorker.writeToFile(strings);
    }

    public Book[] loadBooks() throws ParseException {
        textFileWorker = new TextFileWorker(this.bookPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Book[] books = new Book[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");
                books[i] = new Book(str[0], Integer.parseInt(str[2]), df.parse(str[4]), df.parse(str[5]));
                books[i].setId(Integer.parseInt(str[1]));
                books[i].setTheBookInStore(Boolean.parseBoolean(str[3]));
            }
            return books;
        }
        return null;
    }

    public Reader[] loadReader() {
        textFileWorker = new TextFileWorker(this.readerPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Reader[] readers = new Reader[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");
                readers[i] = new Reader(str[1]);
                readers[i].setId(Integer.parseInt(str[0]));
            }
            return readers;
        }
        return null;
    }

    public Order[] loadOrders(Book[] loadedBooks) throws ParseException {
        textFileWorker = new TextFileWorker(this.orderPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Order[] orders = new Order[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");

                Book[] books = new Book[Integer.parseInt(str[5])];
                for (int j = 0; j < Integer.parseInt(str[5]); j++) {
                    books[j] = ArrayWorker.search(loadedBooks, Integer.parseInt(str[6 + j]));
                }
                orders[i] = new Order(new Reader(str[0]), df.parse(str[4]), books);
                orders[i].setId(Integer.parseInt(str[1]));
                orders[i].setStatus(Status.valueOf(str[2]));
            }
            return orders;
        }
        return null;
    }

    public Request[] loadRequests(Book[] loadedBooks, Reader[] loadedReader) {
        textFileWorker = new TextFileWorker(this.requestPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Request[] requests = new Request[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] str = strings[i].split(" ");
                Book book = (ArrayWorker.search(loadedBooks, Integer.parseInt(str[2])));
                Reader reader = (ArrayWorker.search(loadedReader, Integer.parseInt(str[1])));
                requests[i] = new Request(book, reader);
                requests[i].setId(Integer.parseInt(str[0]));
            }
            return requests;
        }
        return null;
    }
}
