package com.senla.api.dao;

import com.senla.api.model.IOrder;
import com.senla.dao.DaoException;

import java.util.List;

public interface IOrderDao extends IGenericDao<IOrder> {
    Integer getAllPrice() throws Exception;

    Integer getAmountExecutedOrders() throws Exception;

    List<IOrder> getAllExec(String sort) throws Exception;
}
