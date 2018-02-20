package com.senla.api.dao;

import com.senla.api.model.IOrder;

import java.util.List;

public interface IOrderDao extends IGenericDao<IOrder> {
    Integer getAllPrice();

    Integer getAmountExecutedOrders();

    List<IOrder> getAllExec(String sort);
}
