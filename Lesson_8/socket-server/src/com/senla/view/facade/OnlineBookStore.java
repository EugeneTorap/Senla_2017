package com.senla.view.facade;

import com.senla.api.facade.IOnlineBookStore;
import com.senla.api.manager.*;
import com.senla.comparator.book.*;
import com.senla.comparator.order.*;
import com.senla.controller.manager.*;
import com.senla.main.CommonFiller;
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

    /*public void sortBooksBy(SortingType type) {
        switch (type) {
            case ALPHABET:
                bookManager.sortBooks(new SortingBooksByAlphabet());
                break;
            case DATE:
                bookManager.sortBooks(new SortingBooksByDatePub());
                break;
            case PRICE:
                bookManager.sortBooks(new SortingBooksByPrice());
                break;
            case IS_STORE:
                bookManager.sortBooks(new SortingBooksByStore());
                break;
        }
    }*/

    public void sortBooksBy() {
        bookManager.sortBooks(new SortingBooksByAlphabet());
    }

    public void sortUnsoldBooksBy(SortingType type) {
        switch (type) {
            case DATE:
                bookManager.sortUnsoldBooks(new SortingBooksByDateRec());
                break;
            case PRICE:
                bookManager.sortUnsoldBooks(new SortingBooksByPrice());
                break;
        }
    }

    public void sortOrdersBy(SortingType type) {
        switch (type) {
            case DATE:
                orderManager.sortOrders(new SortingOrdersByDate());
                break;
            case PRICE:
                orderManager.sortOrders(new SortingOrdersByPrice());
                break;
            case STATUS:
                orderManager.sortOrders(new SortingOrdersByStatus());
                break;
        }
    }

    public void sortExecutedOrdersBy(SortingType type) {
        switch (type) {
            case PRICE:
                orderManager.sortExecutedOrders(new SortingOrdersByPrice());
                break;
            case DATE:
                orderManager.sortExecutedOrders(new SortingOrdersByDate());
                break;
        }
    }

    public void sortRequestsBy(SortingType type) {
        requestManager.setRequestAmount();
        switch (type) {
            case AMOUNT:
                bookManager.sortBooks(new SortingBooksByAmount());
                break;
            case ALPHABET:
                bookManager.sortBooks(new SortingBooksByAlphabet());
                break;
        }
    }

    public synchronized void addBook(Book newBook){
        bookManager.add(newBook);
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

    public List<Book> getBooks(){
        return bookManager.getBooks();
    }

    public List<Book> getUnsoldBooks(){
        return bookManager.getUnsoldBooks();
    }

    public List<Order> getOrders(){
        return orderManager.getOrders();
    }

    public List<Order> getExecutedOrders(){
        return orderManager.getExecutedOrders();
    }

    public List<Reader> getReaders(){
        return readerManager.getReaders();
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

    public void serialize(){
        bookManager.serialize();
        orderManager.serialize();
        readerManager.serialize();
        requestManager.serialize();
    }

    public void deserialize(){
        bookManager.deserialize();
        orderManager.deserialize();
        readerManager.deserialize();
        requestManager.deserialize();
    }
}
