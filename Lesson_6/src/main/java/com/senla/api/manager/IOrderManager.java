package com.senla.api.manager;

import com.senla.entity.Order;

import java.util.Comparator;

public interface IOrderManager extends IManager {
    void add(Order order);
    void clone(int id);
    void cancel(int id);
    void showOrderInfo(int id);
    int getAllPrice();
    int getAmountExecutedOrders();
    void showOrders();
    void showExecutedOrders();
    void sortOrders(Comparator comparator);
    void sortExecutedOrders(Comparator comparator);
}
