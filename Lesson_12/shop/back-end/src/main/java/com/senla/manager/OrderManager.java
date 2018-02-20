package com.senla.manager;

import com.senla.api.dao.IOrderDao;
import com.senla.api.manager.IOrderManager;
import com.senla.api.model.IOrder;
import com.senla.connector.DBConnector;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import org.apache.log4j.Logger;

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
        orderDao.create(order);
    }

    @Override
    public void delete(int id) {
        IOrder order = getById(id);
        if (order != null){
            orderDao.delete(order);
        }
    }

    @Override
    public IOrder getById(int id) {
        return orderDao.getById(id);
    }

    @Override
    public List<IOrder> getAll(String sort) {
        if (sort == null){
            sort = "id";
        }
        return orderDao.getAll(sort);
    }

    @Override
    public List<IOrder> getAllExec(String sort) {
        if (sort == null){
            sort = "id";
        }
        return orderDao.getAllExec(sort);
    }

    @Override
    public void cancel(int id) {
        //orderDao.update(id);
    }

    @Override
    public Integer getAllPrice(){
        return orderDao.getAllPrice();
    }

    @Override
    public Integer getAmountExecutedOrders(){
        return orderDao.getAmountExecutedOrders();
    }

    @Override
    public void finishOrder(){
    }

    public void clone(int id) {
        try {
            IOrder order = orderDao.getById(id);
            assert order != null;
            orderDao.create(order.clone());
        }
        catch (CloneNotSupportedException e) {
            LOGGER.error("Failed clone", e);
        }
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IOrder.class);

        for (IOrder order: (List<IOrder>) Parser.parse(IOrder.class, lines)){
            orderDao.saveOrUpdate(order);
        }
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
