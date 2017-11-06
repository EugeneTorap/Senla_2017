package util;

import com.danco.training.TextFileWorker;
import entity.Book;
import entity.Order;
import entity.Reader;
import enums.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FileWorker {
    private static TextFileWorker textFileWorker;
    private String bookPath;
    private String orderPath;
    private String readerPath;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public FileWorker(String bookPath, String readerPath, String orderPath) {
        this.bookPath = bookPath;
        this.readerPath = readerPath;
        this.orderPath = orderPath;
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
                strings[i] = readers[i].getName();
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
                String[] bookString = strings[i].split(" ");
                books[i].setTitle(bookString[0]);
                books[i].setId(Integer.parseInt(bookString[1]));
                books[i].setPrice(Integer.parseInt(bookString[2]));
                books[i].setTheBookInStore(Boolean.parseBoolean(bookString[3]));
                books[i].setDatePublished(df.parse(bookString[4]));
                books[i].setDateReceipted(df.parse(bookString[5]));
            }
            return books;
        }
        return null;
    }

    public Order[] loadOrders() throws ParseException {
        textFileWorker = new TextFileWorker(this.orderPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Order[] orders = new Order[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String[] orderString = strings[i].split(" ");
                orders[i].setReader(new Reader(orderString[0]));
                orders[i].setId(Integer.parseInt(orderString[1]));
                orders[i].setStatus(Status.valueOf(orderString[2]));
                orders[i].setDateExecuted(df.parse(orderString[4]));

                Book[] books = new Book[Integer.parseInt(orderString[5])];
                for (int j = 0; j < Integer.parseInt(orderString[5]); j++) {
                    books[i] = ArrayWorker.search(books, Integer.parseInt(orderString[6 + j]));
                }
                orders[i].setBooks(books);
            }
            return orders;
        }
        return null;
    }

    public Reader[] loadReader() {
        textFileWorker = new TextFileWorker(this.readerPath);
        String[] strings = textFileWorker.readFromFile();
        if (strings != null) {
            Reader[] readers = new Reader[strings.length];
            for (int i = 0; i < strings.length; i++) {
                readers[i].setName(strings[i]);
            }
            return readers;
        }
        return null;
    }
}
