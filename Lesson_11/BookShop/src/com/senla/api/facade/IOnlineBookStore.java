package com.senla.api.facade;

import com.senla.model.entity.*;
import com.senla.enums.SortingType;

import java.util.List;

public interface IOnlineBookStore {
    void addReader(Reader reader);
    void addBook(Book newBook);
    void addBookOnStore(int id);
    void delBookFromStore(int id);
    void addOrder(Order order);
    void cloneOrder(int id);
    void cancelOrder(int id);
    void addRequest(Request request);
    int getAllPrice();
    int getAmountExecutedOrders();
    Book getBook(int id);
    Order getOrder(int id);
    Reader getReader(int id);
    List<Request> getRequests();
    List<Book> sortBooksBy(SortingType type);
    List<Book> sortUnsoldBooksBy(SortingType type);
    List<Order> sortOrdersBy(SortingType type);
    List<Order> sortExecutedOrdersBy(SortingType type);
    void sortRequestsBy(SortingType type);
    void saveCSV(List<? extends Entity> entities);
    void loadCSV(Class<? extends Entity> clazz);
}
