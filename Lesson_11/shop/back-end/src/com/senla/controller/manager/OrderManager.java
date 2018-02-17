package com.senla.controller.manager;

import com.senla.api.dao.IOrderDao;
import com.senla.api.manager.IOrderManager;
import com.senla.api.model.IEntity;
import com.senla.api.model.IOrder;
import com.senla.connector.DBConnector;
import com.senla.controller.dao.DaoException;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import com.senla.executor.Executor;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class OrderManager implements IOrderManager {

    private IOrderDao orderDao;
    private final static Logger LOGGER = Logger.getLogger(OrderManager.class);

    public OrderManager() {
        DBConnector connector = DBConnector.getInstance();
        orderDao = (IOrderDao) DependencyInjection.getInstance().getObject(connector, IOrderDao.class);
    }

    @Override
    public void create(IOrder order) {
        try {
            orderDao.create(order);
        } catch (DaoException e) {
            LOGGER.error("Method create(IOrder order) is failed", e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            orderDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error("Method delete(int id) is failed", e);
        }
    }

    @Override
    public IOrder getById(int id) {
        try {
            return orderDao.getById(id);
        } catch (DaoException e) {
            LOGGER.error("Method getById(int id) is failed", e);
        }
        return null;
    }

    @Override
    public List<IOrder> getAll(String sort) {
        try {
            return orderDao.getAll(sort);
        } catch (DaoException e) {
            LOGGER.error("Method getAll(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public List<IOrder> getAllExec(String sort) {
        try {
            return orderDao.getAllExec(sort);
        } catch (DaoException e) {
            LOGGER.error("Method getAllExec(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public void cancel(int id) {
        try {
            orderDao.update(id);
        } catch (DaoException e) {
            LOGGER.error("Method cancel(int id) is failed", e);
        }
    }

    @Override
    public Integer getAllPrice(){
        try {
            Integer allPrice = orderDao.getAllPrice();
            if (allPrice != null){
                return allPrice;
            }
        } catch (DaoException e) {
            LOGGER.error("Method getAllPrice() is failed", e);
        }
        return null;
    }

    @Override
    public Integer getAmountExecutedOrders(){
        try {
            Integer count = orderDao.getAmountExecutedOrders();
            if (count != null){
                return count;
            }
        } catch (DaoException e) {
            LOGGER.error("Method getAmountExecutedOrders() is failed", e);
        }
        return null;
    }

    @Override
    public void finishOrder(){
        String[] sql = {"UPDATE reader SET balance = balance - 10 WHERE readerId IN (1, 2, 4);",
        "UPDATE book_order SET status = 'EXECUTED' WHERE orderId IN (1, 2, 4);"};

        LOGGER.trace("Open connection");
        Connection connection = DBConnector.getInstance().getConnection();
        try {
            Executor.execUpdate(connection, sql);
        } catch (DaoException e) {
            LOGGER.error("Method finishOrder() is failed", e);
        }
    }

    public void clone(int id) {
        try {
            IOrder order = orderDao.getById(id);
            assert order != null;
            orderDao.create(order.clone());
        }
        catch (CloneNotSupportedException | DaoException e) {
            LOGGER.error("Failed clone", e);
        }
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IOrder.class);
        List<IOrder> parsedReaders = (List<IOrder>) Parser.parse(IOrder.class, lines);
        CSVWorker.setEntity((List<IEntity>)(List<?>)parsedReaders, (List<IEntity>)(List<?>)getAll(null));
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
