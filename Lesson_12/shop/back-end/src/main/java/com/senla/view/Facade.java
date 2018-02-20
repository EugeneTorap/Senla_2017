package com.senla.view;

import com.senla.api.facade.IFacade;
import com.senla.api.manager.*;
import com.senla.api.model.*;
import com.senla.di.DependencyInjection;
import com.senla.enums.SortingType;

import java.util.List;

public class Facade implements IFacade {
    private IBookManager bookManager;
    private IReaderManager readerManager;
    private IOrderManager orderManager;
    private static volatile Facade bookStore = null;


    private Facade(){
        bookManager = (IBookManager) DependencyInjection.getInstance().getObject(IBookManager.class);
        readerManager = (IReaderManager) DependencyInjection.getInstance().getObject(IReaderManager.class);
        orderManager = (IOrderManager) DependencyInjection.getInstance().getObject(IOrderManager.class);
    }

    public static Facade getInstance() {
        if (bookStore == null) {
            synchronized (Facade.class) {
                if (bookStore == null) {
                    bookStore = new Facade();
                }
            }
        }
        return bookStore;
    }

    @Override
    public List<IBook> sortBooksBy(SortingType type) {

        switch (type) {
            case ALPHABET:
                return bookManager.getAll("title");
            case DATE:
                return bookManager.getAll("datePublished");
            case PRICE:
                return bookManager.getAll("price");
            case IS_STORE:
                return bookManager.getAll("isStore");
        }
        return null;
    }

    @Override
    public List<IBook> sortUnsoldBooksBy(SortingType type) {

        switch (type) {
            case DATE:
                return bookManager.getAllUnsold("dateReceipted");
            case PRICE:
                return bookManager.getAllUnsold("price");
        }
        return null;
    }

    @Override
    public List<IOrder> sortOrdersBy(SortingType type) {

        switch (type) {
            case DATE:
                return orderManager.getAll("dateExecuted");
            case PRICE:
                return orderManager.getAll("price");
            case STATUS:
                return orderManager.getAll("status");
        }
        return null;
    }

    @Override
    public List<IOrder> sortExecutedOrdersBy(SortingType type) {

        switch (type) {
            case PRICE:
                return orderManager.getAllExec("price");
            case DATE:
                return orderManager.getAllExec("dateExecuted");
        }
        return null;
    }

    @Override
    public List<IBook> sortRequestsBy(SortingType type) {
        String query = "SELECT * FROM request INNER JOIN book USING (bookId) JOIN reader USING (readerId) ORDER BY ";

        switch (type) {
            case AMOUNT:
                return bookManager.getAll("");
            case ALPHABET:
                return bookManager.getAll("title");
        }
        return null;
    }

    @Override
    public List<IReader> sortReadersBy(SortingType type) {

        switch (type) {
            case ALPHABET:
                return readerManager.getAll("name");
            case BALANCE:
                return readerManager.getAll("balance");
        }
        return null;
    }

    @Override
    public synchronized void addBook(IBook book){
        bookManager.create(book);
    }

    @Override
    public synchronized void addOrder(IOrder order) {
        orderManager.create(order);
    }

    @Override
    public synchronized void addReader(IReader reader){
        readerManager.create(reader);
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
    public IBook getBook(Integer id){
        return bookManager.getById(id);
    }

    @Override
    public IOrder getOrder(Integer id){
        return orderManager.getById(id);
    }

    @Override
    public IReader getReader(Integer id){
        return readerManager.getById(id);
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
    public void finishOrder(){
        orderManager.finishOrder();
    }

    @Override
    public void importBooks() {
        bookManager.importFromCsv();
    }

    @Override
    public void importOrders() {
        orderManager.importFromCsv();
    }

    @Override
    public void importReaders() {
        readerManager.importFromCsv();
    }

    @Override
    public void exportBooks() {
        bookManager.exportToCsv();
    }

    @Override
    public void exportOrders() {
        orderManager.exportToCsv();
    }

    @Override
    public void exportReaders() {
        readerManager.exportToCsv();
    }
}
