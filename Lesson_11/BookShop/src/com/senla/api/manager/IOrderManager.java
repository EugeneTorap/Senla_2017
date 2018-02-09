package com.senla.api.manager;

import com.senla.model.entity.Order;

import java.util.Comparator;
import java.util.List;

public interface IOrderManager {
    void add(Order order);
    void clone(int id);
    void cancel(int id);
    int getAllPrice();
    int getAmountExecutedOrders();
    Order findById(int id);
    List<Order> sortOrders(String query, String column);
}
