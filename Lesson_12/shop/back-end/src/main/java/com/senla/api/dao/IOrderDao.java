package com.senla.api.dao;

import com.senla.api.model.IOrder;
import org.hibernate.Session;

import java.util.List;

public interface IOrderDao extends IGenericDao<IOrder> {
    Integer getAllPrice(Session session);

    Integer getAmountExecutedOrders(Session session);

    List<IOrder> getAllExec(Session session, String sort);
}
