package com.senla.shop.api.facade;

import com.senla.shop.api.model.*;
import com.senla.shop.enums.SortingType;

import java.util.List;

public interface IFacade {
    void addReader(IReader reader);
    void addBook(IBook book);
    void addOrder(IOrder order);
    void cloneOrder(int id);
    void cancelOrder(int id);
    Integer getAllPrice();
    Integer getAmountExecutedOrders();
    IBook getBook(Integer id);
    IOrder getOrder(Integer id);
    IReader getReader(Integer id);
    List<IBook> sortBooksBy(SortingType type);
    List<IBook> sortUnsoldBooksBy(SortingType type);
    List<IOrder> sortOrdersBy(SortingType type);
    List<IOrder> sortExecutedOrdersBy(SortingType type);
    List<IBook> sortRequestsBy(SortingType type);
    List<IReader> sortReadersBy(SortingType type);
    void finishOrder();
    void importBooks();
    void importOrders();
    void importReaders();
    void exportBooks();
    void exportOrders();
    void exportReaders();
}
