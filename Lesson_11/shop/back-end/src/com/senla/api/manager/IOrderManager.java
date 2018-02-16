package com.senla.api.manager;

import com.senla.api.model.IOrder;

import java.util.List;

public interface IOrderManager extends IManager<IOrder> {
    void cancel(int id);

    Integer getAllPrice();

    Integer getAmountExecutedOrders();

    void finishOrder();

    void clone(int id);

    List<IOrder> getAllExec(String sort);
}
