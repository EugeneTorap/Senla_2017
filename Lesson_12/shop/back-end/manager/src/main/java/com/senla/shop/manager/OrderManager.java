package com.senla.shop.manager;

import com.senla.shop.api.dao.IOrderDao;
import com.senla.shop.api.manager.IOrderManager;
import com.senla.shop.api.model.IOrder;
import com.senla.shop.csv.CSVWorker;
import com.senla.shop.csv.Parser;
import com.senla.shop.di.DependencyInjection;
import com.senla.shop.executor.Executor;

import java.util.List;

public class OrderManager implements IOrderManager {

    private IOrderDao orderDao;

    public OrderManager() {
        orderDao = (IOrderDao) DependencyInjection.getInstance().getObject(IOrderDao.class);
    }

    @Override
    public void create(IOrder order) throws Exception {
        Executor.transact(session -> {
            orderDao.create(session, order);
            return null;
        });
    }

    @Override
    public void delete(int id) throws Exception {
        Executor.transact(session -> {
            IOrder order = orderDao.getById(session, id);
            if (order != null){
                orderDao.delete(session, order);
            }
            return null;
        });
    }

    @Override
    public IOrder getById(int id) throws Exception {
        return Executor.transact(session -> orderDao.getById(session, id));
    }

    @Override
    public List<IOrder> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> orderDao.getAll(session, sorting));
    }

    @Override
    public List<IOrder> getAllExec(String sort) throws Exception {
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
    public Integer getAllPrice() throws Exception {
        return Executor.transact(session -> orderDao.getAllPrice(session));
    }

    @Override
    public Integer getAmountExecutedOrders() throws Exception {
        return Executor.transact(session -> orderDao.getAmountExecutedOrders(session));
    }

    @Override
    public void finishOrder(){
    }

    public void clone(int id) throws Exception {
        Executor.transact(session -> {
            IOrder order = orderDao.getById(session, id);
            assert order != null;
            orderDao.create(session, order.clone());
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() throws Exception {
        List<String> lines = CSVWorker.loadCsvStrings(IOrder.class);

        Executor.transact(session -> {
            for (IOrder order: (List<IOrder>) Parser.parse(IOrder.class, lines)){
                orderDao.saveOrUpdate(session, order);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() throws Exception {
        CSVWorker.saveToCSV(getAll(null));
    }
}
