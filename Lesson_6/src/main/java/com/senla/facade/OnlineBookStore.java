package com.senla.facade;

import com.senla.api.facade.IOnlineBookStore;
import com.senla.api.manager.*;
import com.senla.comparator.book.*;
import com.senla.comparator.order.*;
import com.senla.controller.manager.*;
import com.senla.entity.*;
import com.senla.util.Printer;
import com.senla.enums.SortingType;

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
            synchronized (OnlineBookStore.class){
                if (bookStore == null) {
                    bookStore = new OnlineBookStore();
                    bookStore.loadAllData();
                }
            }
        }
        return bookStore;
    }

    public void showBooksSortedBy(SortingType type) {
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
        bookManager.showBooks();
    }

    public void showUnsoldBooksSortedBy(SortingType type) {
        switch (type) {
            case DATE:
                bookManager.sortUnsoldBooks(new SortingBooksByDateRec());
                break;
            case PRICE:
                bookManager.sortUnsoldBooks(new SortingBooksByPrice());
                break;
        }
        bookManager.showUnsoldBooks();
    }

    public void showOrdersSortedBy(SortingType type) {
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
        orderManager.showOrders();
    }

    public void showExecutedOrdersSortedBy(SortingType type) {
        switch (type) {
            case PRICE:
                orderManager.sortExecutedOrders(new SortingOrdersByPrice());
                break;
            case DATE:
                orderManager.sortExecutedOrders(new SortingOrdersByDate());
                break;
        }
        orderManager.showExecutedOrders();
    }

    public void showRequestsSortedBy(SortingType type) {
        requestManager.setRequestAmount();
        switch (type) {
            case AMOUNT:
                bookManager.sortBooks(new SortingBooksByAmount());
                break;
            case ALPHABET:
                bookManager.sortBooks(new SortingBooksByAlphabet());
                break;
        }
        requestManager.showBookRequests();
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

    public Book searchBook(int id){
        return bookManager.search(id);
    }

    public Reader searchReader(int id){
        return readerManager.search(id);
    }

    public void showAllPrice(){
        Printer.print(orderManager.getAllPrice());
    }

    public void showAmountExecutedOrders(){
        Printer.print(orderManager.getAmountExecutedOrders());
    }

    public void showOrderInfo(int id) {
        orderManager.showOrderInfo(id);
    }

    public void showBookInfo(int id) {
        bookManager.showBookInfo(id);
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

    public void saveAllData(){
        bookManager.saveToFile();
        orderManager.saveToFile();
        requestManager.saveToFile();
        readerManager.saveToFile();
    }

    public void loadAllData(){
        bookManager.loadFromFile();
        readerManager.loadFromFile();
        orderManager.loadFromFile();
        requestManager.loadFromFile();
    }
}
