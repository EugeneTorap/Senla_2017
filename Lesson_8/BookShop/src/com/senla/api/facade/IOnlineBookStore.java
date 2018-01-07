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
    List<Book> getBooks();
    List<Book> getUnsoldBooks();
    List<Order> getOrders();
    List<Order> getExecutedOrders();
    List<Reader> getReaders();
    List<Request> getRequests();
    void sortBooksBy(SortingType type);
    void sortUnsoldBooksBy(SortingType type);
    void sortOrdersBy(SortingType type);
    void sortExecutedOrdersBy(SortingType type);
    void sortRequestsBy(SortingType type);
    void saveCSV(List<? extends Entity> entities);
    void loadCSV(Class<? extends Entity> clazz);
    void serialize();
    void deserialize();
}
