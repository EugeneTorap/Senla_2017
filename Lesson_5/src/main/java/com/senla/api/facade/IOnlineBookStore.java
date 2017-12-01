package com.senla.api.facade;

import com.senla.entity.Book;
import com.senla.entity.Order;
import com.senla.entity.Reader;
import com.senla.entity.Request;
import com.senla.enums.SortingType;

public interface IOnlineBookStore {
    void showBooksSortedBy(SortingType type);
    void showUnsoldBooksSortedBy(SortingType type);
    void showOrdersSortedBy(SortingType type);
    void showExecutedOrdersSortedBy(SortingType type);
    void showRequestsSortedBy(SortingType type);
    void showAllPrice();
    void showAmountExecutedOrders();
    void showOrderInfo(int id);
    void showBookInfo(int id);
    void addReader(Reader reader);
    void addBook(Book newBook);
    void addBookOnStore(int id);
    void delBookFromStore(int id);
    void addOrder(Order order);
    void cancelOrder(int id);
    void addRequest(Request request);
    Book searchBook(int id);
    Reader searchReader(int id);
    void saveAllData();
    void loadAllData();
}
