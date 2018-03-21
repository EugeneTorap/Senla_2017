package com.senla.shop.api.manager;

import com.senla.shop.model.Order;

import java.util.List;

public interface IOrderManager extends IManager<Order> {
    void cancel(int id);

    Integer getAllPrice() throws Exception;

    Integer getAmountExecutedOrders() throws Exception;

    void finishOrder();

    void clone(int id) throws Exception;

    List<Order> getAllExec(String sort) throws Exception;
}
