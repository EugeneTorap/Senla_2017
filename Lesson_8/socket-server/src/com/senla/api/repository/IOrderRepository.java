package com.senla.api.repository;

import com.senla.model.entity.Order;

import java.util.List;

public interface IOrderRepository {
    void add(Order order);
    void cancel(int id);
    void clone(int id);
    List<Order> getOrders();
    void setOrders(List<Order> orders);
    List<Order> getExecutedOrders();
}
