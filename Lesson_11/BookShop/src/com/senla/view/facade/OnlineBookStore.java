package com.senla.view.facade;

import com.senla.api.facade.IOnlineBookStore;
import com.senla.api.manager.*;
import com.senla.controller.manager.*;
import com.senla.csv.CSVWorker;
import com.senla.model.entity.*;
import com.senla.enums.SortingType;

import java.util.List;

public class OnlineBookStore implements IOnlineBookStore{
    private IBookManager bookManager;
    private IReaderManager readerManager;
    private IOrderManager orderManager;
    private static volatile OnlineBookStore bookStore = null;


    private OnlineBookStore(){
        bookManager = new BookManager();
        readerManager = new ReaderManager();
        orderManager = new OrderManager();
    }

    public static OnlineBookStore getInstance() {
        if (bookStore == null) {
            synchronized (OnlineBookStore.class) {
                if (bookStore == null) {
                    bookStore = new OnlineBookStore();
                }
            }
        }
        return bookStore;
    }

    @Override
    public List<Book> sortBooksBy(SortingType type) {
        String query = "SELECT * FROM book ORDER BY ";

        switch (type) {
            case ALPHABET:
                return bookManager.sortBooks(query, "title");
            case DATE:
                return bookManager.sortBooks(query, "datePublished");
            case PRICE:
                return bookManager.sortBooks(query, "price");
            case IS_STORE:
                return bookManager.sortBooks(query, "isTheBookInStore");
        }
        return null;
    }

    @Override
    public List<Book> sortUnsoldBooksBy(SortingType type) {
        String query = "SELECT * FROM book WHERE dateReceipted < (SELECT DATE_SUB((SELECT CURDATE()), INTERVAL 6 MONTH)) ORDER BY ";

        switch (type) {
            case DATE:
                return bookManager.sortBooks(query, "dateReceipted");
            case PRICE:
                return bookManager.sortBooks(query, "price");
        }
        return null;
    }

    @Override
    public List<Order> sortOrdersBy(SortingType type) {
        String query = "SELECT * FROM book_order INNER JOIN reader USING (readerId) ORDER BY ";

        switch (type) {
            case DATE:
                return orderManager.sortOrders(query, "dateExecuted");
            case PRICE:
                return orderManager.sortOrders(query, "price");
            case STATUS:
                return orderManager.sortOrders(query, "status");
        }
        return null;
    }

    @Override
    public List<Order> sortExecutedOrdersBy(SortingType type) {
        String query = "SELECT * FROM book_order INNER JOIN reader USING (readerId) WHERE status = 'EXECUTED' ORDER BY ";

        switch (type) {
            case PRICE:
                return orderManager.sortOrders(query, "price");
            case DATE:
                return orderManager.sortOrders(query, "dateExecuted");
        }
        return null;
    }

    @Override
    public List<Book> sortRequestsBy(SortingType type) {
        String query = "SELECT * FROM request INNER JOIN book USING (bookId) JOIN reader USING (readerId) ORDER BY ";

        switch (type) {
            case AMOUNT:
                return bookManager.sortBooks(query, "");
            case ALPHABET:
                return bookManager.sortBooks(query, "title");
        }
        return null;
    }

    @Override
    public List<Reader> sortReadersBy(SortingType type) {
        String query = "SELECT * FROM reader WHERE ORDER BY ";

        switch (type) {
            case ID:
                return readerManager.sortReaders(query, "readerId");
        }
        return null;
    }

    @Override
    public synchronized void addBook(Book book){
        bookManager.add(book);
    }

    @Override
    public synchronized void addBookOnStore(int id) {
        bookManager.addOnStore(id);
    }

    @Override
    public synchronized void delBookFromStore(int id) {
        bookManager.delFromStore(id);
    }

    @Override
    public synchronized void addOrder(Order order) {
        orderManager.add(order);
    }

    @Override
    public synchronized void cloneOrder(int id){
        orderManager.clone(id);
    }

    @Override
    public synchronized void cancelOrder(int id) {
        orderManager.cancel(id);
    }

    @Override
    public synchronized void addReader(Reader reader){
        readerManager.add(reader);
    }

    @Override
    public synchronized void addRequest(Book book, Reader reader){
        bookManager.add(book, reader);
    }

    @Override
    public Book getBook(int id){
        return bookManager.findById(id);
    }

    @Override
    public Order getOrder(int id){
        return orderManager.findById(id);
    }

    @Override
    public Reader getReader(int id){
        return readerManager.findById(id);
    }

    @Override
    public int getAllPrice(){
        return orderManager.getAllPrice();
    }

    @Override
    public int getAmountExecutedOrders(){
        return orderManager.getAmountExecutedOrders();
    }

    @Override
    public void saveCSV(List<? extends Entity> entities){
        CSVWorker.saveToCSV(entities);
    }

    @Override
    public void loadCSV(Class<? extends Entity> clazz){
        CSVWorker.loadFromCSV(clazz);
    }
}
