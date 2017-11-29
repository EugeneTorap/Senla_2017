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
    private static OnlineBookStore bookStore = null;


    private OnlineBookStore(){
        bookManager = new BookManager();
        readerManager = new ReaderManager();
        orderManager = new OrderManager();
        requestManager = new RequestManager();
    }

    public static OnlineBookStore getInstance() {
        if (bookStore == null) {
            bookStore = new OnlineBookStore();
            bookStore.deserialize();
        }
        return bookStore;
    }

    public void sortBooksBy(SortingType type) {
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

    public void addBook(Book newBook){
        bookManager.add(newBook);
    }

    public void addBookOnStore(int id) {
        bookManager.addOnStore(id);
    }

    public void delBookFromStore(int id) {
        bookManager.delFromStore(id);
    }

    public void addOrder(Order order) {
        orderManager.add(order);
    }

    public void cloneOrder(int id){
        orderManager.clone(id);
    }

    public void cancelOrder(int id) {
        orderManager.cancel(id);
    }

    public void addReader(Reader reader){
        readerManager.add(reader);
    }

    public void addRequest(Request request){
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

    public void exportBook(){
        bookManager.exportToFile();
    }

    public void importBook(){
        bookManager.importFromFile();
    }

    public void exportOrder(){
        orderManager.exportToFile();
    }

    public void importOrder(){
        orderManager.importFromFile();
    }

    public void exportReader(){
        readerManager.exportToFile();
    }

    public void importReader(){
        readerManager.importFromFile();
    }

    public void exportRequest(){
        requestManager.exportToFile();
    }

    public void importRequest(){
        requestManager.importFromFile();
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
