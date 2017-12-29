package com.senla.api.manager;

import com.senla.model.entity.Order;

import java.util.Comparator;
import java.util.List;

public interface IOrderManager extends IManager {
    void add(Order order);
    void clone(int id);
    void cancel(int id);
    int getAllPrice();
    int getAmountExecutedOrders();
    List<Order> getOrders();
    List<Order> getExecutedOrders();
    void sortOrders(Comparator comparator);
    void sortExecutedOrders(Comparator comparator);
}
