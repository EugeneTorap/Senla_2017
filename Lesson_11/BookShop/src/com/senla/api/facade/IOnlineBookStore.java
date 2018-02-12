package com.senla.api.facade;

import com.senla.model.entity.*;
import com.senla.enums.SortingType;

import java.util.List;

public interface IOnlineBookStore {
    void addReader(Reader reader);
    void addRequest(Book book, Reader reader);
    void addBook(Book newBook);
    void addBookOnStore(int id);
    void delBookFromStore(int id);
    void addOrder(Order order);
    void cloneOrder(int id);
    void cancelOrder(int id);
    int getAllPrice();
    int getAmountExecutedOrders();
    Book getBook(Integer id);
    Order getOrder(Integer id);
    Reader getReader(Integer id);
    List<Book> sortBooksBy(SortingType type);
    List<Book> sortUnsoldBooksBy(SortingType type);
    List<Order> sortOrdersBy(SortingType type);
    List<Order> sortExecutedOrdersBy(SortingType type);
    List<Book> sortRequestsBy(SortingType type);
    List<Reader> sortReadersBy(SortingType type);
    void finishOrder();
    void saveCSV(List<? extends Entity> entities);
    void loadCSV(Class<? extends Entity> clazz);
}
