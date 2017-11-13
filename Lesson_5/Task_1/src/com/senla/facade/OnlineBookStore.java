package com.senla.facade;

import com.senla.comparator.book.*;
import com.senla.comparator.order.*;
import com.senla.manager.*;
import com.senla.entity.*;
import com.senla.util.Printer;
import com.senla.enums.SortingType;

import java.text.ParseException;
import java.util.List;

public class OnlineBookStore {
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private OrderManager orderManager = new OrderManager(bookManager);
    private RequestManager requestManager = new RequestManager(readerManager, bookManager);


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
        showBookRequests(bookManager.getBooks());
    }

    private void showBookRequests(List<Book> books){
        System.out.println();
        for (Book book : books) {
            System.out.println(book.getTitle() + " " + book.getRequestAmount());
            if (requestManager.requestForBook(book) != null){
                for (Request request : requestManager.requestForBook(book)) {
                    System.out.println(request.getId() + " " + request.getReader().getName());
                }
            }
            System.out.println("-----------------------");
        }
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

    public void addReader(Reader reader){
        readerManager.addReader(reader);
    }

    public void addBook(Book newBook){
        bookManager.addBook(newBook);
    }

    public void addBookOnStore(int id) {
        bookManager.addBookOnStore(id);
    }

    public void delBookFromStore(int id) {
        bookManager.delBookFromStore(id);
    }

    public void addOrder(Order order) {
        orderManager.addOrder(order);
    }

    public void cancelOrder(int id) {
        orderManager.cancelOrder(id);
    }

    public void addRequest(Request request){
        requestManager.addRequest(request);
    }

    public void saveAllData(){
        bookManager.saveToFile();
        orderManager.saveToFile();
        requestManager.saveToFile();
        readerManager.saveToFile();
    }

    public void loadAllData() throws ParseException {
        bookManager.loadFromFile();
        readerManager.loadFromFile();
        orderManager.loadFromFile();
        requestManager.loadFromFile();
    }
}
