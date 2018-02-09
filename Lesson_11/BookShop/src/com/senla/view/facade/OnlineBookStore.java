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
    private IRequestManager requestManager;
    private static volatile OnlineBookStore bookStore = null;


    private OnlineBookStore(){
        bookManager = new BookManager();
        readerManager = new ReaderManager();
        orderManager = new OrderManager();
        requestManager = new RequestManager();
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

    public List<Book> sortBooksBy(SortingType type) {
        String query = "SELECT * FROM book ORDER BY ";

        switch (type) {
            case ALPHABET:
                return bookManager.sortBooks(query, "name");
            case DATE:
                return bookManager.sortBooks(query, "datePublished");
            case PRICE:
                return bookManager.sortBooks(query, "price");
            case IS_STORE:
                return bookManager.sortBooks(query, "isTheBookInStore");
        }
        return null;
    }

    public List<Book> sortUnsoldBooksBy(SortingType type) {
        String query = "SELECT * FROM book " +
                "WHERE dateReceipted < (SELECT DATE_SUB((SELECT CURDATE()), INTERVAL 6 MONTH)) ORDER BY ";

        switch (type) {
            case DATE:
                return bookManager.sortBooks(query, "dateReceipted");
            case PRICE:
                return bookManager.sortBooks(query, "price");
        }
        return null;
    }

    public List<Order> sortOrdersBy(SortingType type) {
        String query = "SELECT * FROM book_order ORDER BY ";

        switch (type) {
            case DATE:
                return orderManager.sortOrders(query, "dateExecuted");
            case PRICE:
                return orderManager.sortOrders(query, "price");
            case STATUS:
                return orderManager.sortOrders(query, "");
        }
        return null;
    }

    public List<Order> sortExecutedOrdersBy(SortingType type) {
        String query = "SELECT * FROM book_order WHERE status = CANCELED ORDER BY ";

        switch (type) {
            case PRICE:
                return orderManager.sortOrders(query, "price");
            case DATE:
                return orderManager.sortOrders(query, "dateExecuted");
        }
        return null;
    }

    public void sortRequestsBy(SortingType type) {
        //requestManager.setRequestAmount();
        switch (type) {
            case AMOUNT:
                bookManager.sortBooks(new SortingBooksByAmount());
                break;
            case ALPHABET:
                bookManager.sortBooks(new SortingBooksByAlphabet());
                break;
        }
    }

    public synchronized void addBook(Book book){
        bookManager.add(book);
    }

    public synchronized void addBookOnStore(int id) {
        bookManager.addOnStore(id);
    }

    public synchronized void delBookFromStore(int id) {
        bookManager.delFromStore(id);
    }

    public synchronized void addOrder(Order order) {
        orderManager.add(order);
    }

    public synchronized void cloneOrder(int id){
        orderManager.clone(id);
    }

    public synchronized void cancelOrder(int id) {
        orderManager.cancel(id);
    }

    public synchronized void addReader(Reader reader){
        readerManager.add(reader);
    }

    public synchronized void addRequest(Request request){
        requestManager.add(request);
    }

    public Book getBook(int id){
        return bookManager.findById(id);
    }

    public Order getOrder(int id){
        return orderManager.findById(id);
    }

    public Reader getReader(int id){
        return readerManager.findById(id);
    }

    public List<Request> getRequests(){
        return requestManager.getRequests();
    }

    public int getAllPrice(){
        return orderManager.getAllPrice();
    }

    public int getAmountExecutedOrders(){
        return orderManager.getAmountExecutedOrders();
    }

    public void saveCSV(List<? extends Entity> entities){
        CSVWorker.save(entities);
    }

    public void loadCSV(Class<? extends Entity> clazz){
        CSVWorker.load(clazz);
    }
}
