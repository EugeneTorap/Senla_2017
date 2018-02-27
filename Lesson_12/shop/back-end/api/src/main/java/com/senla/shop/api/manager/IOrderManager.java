package com.senla.shop.api.manager;

import com.senla.shop.api.model.IOrder;

import java.util.List;

public interface IOrderManager extends IManager<IOrder> {
    void cancel(int id);

    Integer getAllPrice() throws Exception;

    Integer getAmountExecutedOrders() throws Exception;

    void finishOrder();

    void clone(int id) throws Exception;

    List<IOrder> getAllExec(String sort) throws Exception;
}
