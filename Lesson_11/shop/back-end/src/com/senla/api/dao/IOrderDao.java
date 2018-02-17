package com.senla.api.dao;

import com.senla.api.model.IOrder;
import com.senla.controller.dao.DaoException;

import java.util.List;

public interface IOrderDao extends GenericDao<IOrder> {
    Integer getAllPrice() throws DaoException;

    Integer getAmountExecutedOrders() throws DaoException;

    List<IOrder> getAllExec(String sort) throws DaoException;
}
