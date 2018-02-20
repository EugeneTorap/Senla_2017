package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.api.model.IOrder;

import com.senla.executor.Executor;
import org.hibernate.query.Query;
import java.util.List;

public class OrderDao extends GenericDao<IOrder> implements IOrderDao {

    protected OrderDao(Class clazz) {
        super(clazz);
    }

    @Override
    public Integer getAllPrice() {
        return Executor.transact(session -> {
            Query query = session.createQuery("SELECT SUM(Order.price) AS allPrice FROM Order");
            return (Integer) query.getResultList().get(0);
        });
    }

    @Override
    public Integer getAmountExecutedOrders() {
        return Executor.transact(session -> {
            Query query = session.createQuery("SELECT COUNT(*) AS count FROM Order WHERE Order.status = 'CANCELED'");
            return (Integer) query.getResultList().get(0);
        });
    }

    @Override
    public List<IOrder> getAllExec(String sort) {
        return Executor.transact(session -> {
            Query query = session.createQuery("FROM Order WHERE Order.status = 'EXECUTED' ORDER BY :order ASC");
            query.setParameter("order", sort);
            return (List<IOrder>) query.getResultList();
        });
    }
}
