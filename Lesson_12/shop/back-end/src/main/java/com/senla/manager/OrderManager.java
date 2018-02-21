package com.senla.manager;

import com.senla.api.dao.IOrderDao;
import com.senla.api.manager.IOrderManager;
import com.senla.api.model.IOrder;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import com.senla.executor.Executor;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderManager implements IOrderManager {

    private IOrderDao orderDao;
    private final static Logger LOGGER = Logger.getLogger(OrderManager.class);

    public OrderManager() {
        orderDao = (IOrderDao) DependencyInjection.getInstance().getObject(IOrderDao.class);
    }

    @Override
    public void create(IOrder order) {
        Executor.transact(session -> {
            orderDao.create(session, order);
            return null;
        });
    }

    @Override
    public void delete(int id) {
        Executor.transact(session -> {
            IOrder order = orderDao.getById(session, id);
            if (order != null){
                orderDao.delete(session, order);
            }
            return null;
        });
    }

    @Override
    public IOrder getById(int id) {
        return Executor.transact(session -> orderDao.getById(session, id));
    }

    @Override
    public List<IOrder> getAll(String sort) {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> orderDao.getAll(session, sorting));
    }

    @Override
    public List<IOrder> getAllExec(String sort) {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> orderDao.getAllExec(session, sorting));
    }

    @Override
    public void cancel(int id) {
        //orderDao.update(id);
    }

    @Override
    public Integer getAllPrice(){
        return Executor.transact(session -> orderDao.getAllPrice(session));
    }

    @Override
    public Integer getAmountExecutedOrders(){
        return Executor.transact(session -> orderDao.getAmountExecutedOrders(session));
    }

    @Override
    public void finishOrder(){
    }

    public void clone(int id) {
        Executor.transact(session -> {
            try {
                IOrder order = orderDao.getById(session, id);
                assert order != null;
                orderDao.create(session, order.clone());
            } catch (CloneNotSupportedException e) {
                LOGGER.error("Failed clone", e);
            }
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IOrder.class);

        Executor.transact(session -> {
            for (IOrder order: (List<IOrder>) Parser.parse(IOrder.class, lines)){
                orderDao.saveOrUpdate(session, order);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
