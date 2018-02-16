package com.senla.controller.manager;

import com.senla.api.dao.IOrderDao;
import com.senla.api.manager.IOrderManager;
import com.senla.api.model.IOrder;
import com.senla.connector.DBConnector;
import com.senla.di.DependencyInjection;
import com.senla.executor.Executor;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class OrderManager implements IOrderManager{
    private IOrderDao orderDao;
    private final static Logger LOGGER = Logger.getLogger(OrderManager.class);

    public OrderManager() {
        DBConnector connector = DBConnector.getInstance();
        orderDao = (IOrderDao) DependencyInjection.getInstance().getObject(connector, IOrderDao.class);
    }

    @Override
    public void create(IOrder order) {
        orderDao.create(order);
    }

    @Override
    public void delete(int id) {
        orderDao.delete(id);
    }

    @Override
    public IOrder getById(int id) {
        return orderDao.getById(id);
    }

    @Override
    public List<IOrder> getAll(String sort) {
        return orderDao.getAll(sort);
    }

    @Override
    public List<IOrder> getAllExec(String sort) {
        return orderDao.getAllExec(sort);
    }

    @Override
    public void cancel(int id) {
        orderDao.update(id);
    }

    @Override
    public Integer getAllPrice(){
        Integer allPrice = orderDao.getAllPrice();
        if (allPrice != null){
            return allPrice;
        }
        LOGGER.warn("Sum of all price is null");
        return null;
    }

    @Override
    public Integer getAmountExecutedOrders(){
        Integer count = orderDao.getAmountExecutedOrders();
        if (count != null){
            return count;
        }
        LOGGER.warn("count of executed orders is null");
        return null;
    }

    @Override
    public void finishOrder(){
        String[] sql = {"UPDATE reader SET balance = balance - 10 WHERE readerId IN (1, 2, 4);",
        "UPDATE book_order SET status = 'EXECUTED' WHERE orderId IN (1, 2, 4);"};

        LOGGER.trace("Open connection");
        Connection connection = DBConnector.getInstance().getConnection();
        Executor.execUpdate(connection, sql);
    }

    public void clone(int id) {
        IOrder order = orderDao.getById(id);
        assert order != null;
        try {
            orderDao.create(order.clone());
        }
        catch (CloneNotSupportedException e) {
            LOGGER.error("Failed clone", e);
        }
    }

    @Override
    public String importToCsv() {
        return null;
    }

    @Override
    public String exportFromCsv() {
        return null;
    }
}
