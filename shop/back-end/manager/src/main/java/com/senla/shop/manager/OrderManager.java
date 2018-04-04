package com.senla.shop.manager;

import com.senla.shop.api.dao.IOrderDao;
import com.senla.shop.api.manager.IOrderManager;
import com.senla.shop.csv.CSVWorker;
import com.senla.shop.csv.Parser;
import com.senla.shop.dao.hibernateutil.HibernateUtil;
import com.senla.shop.di.DependencyInjection;
import com.senla.shop.model.Order;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderManager implements IOrderManager {

    private IOrderDao orderDao;
    private final static Logger LOGGER = Logger.getLogger(OrderManager.class);

    public OrderManager() {
        orderDao = (IOrderDao) DependencyInjection.getInstance().getObject(IOrderDao.class);
    }

    @Override
    public void create(Order order) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            orderDao.create(session, order);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order order = orderDao.getById(session, id);
            if (order != null){
                orderDao.delete(session, order);
            }
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public Order getById(int id) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order order = orderDao.getById(session, id);
            transaction.commit();
            return order;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public List<Order> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Order> orders = orderDao.getAll(session, sort);
            transaction.commit();
            return orders;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public List<Order> getAllExec(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Order> orders = orderDao.getAllExec(session, sort);
            transaction.commit();
            return orders;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public void cancel(int id) {
        //orderDao.update(id);
    }

    @Override
    public Integer getAllPrice() throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Integer price = orderDao.getAllPrice(session);
            transaction.commit();
            return price;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public Integer getAmountExecutedOrders() throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Integer amount = orderDao.getAmountExecutedOrders(session);
            transaction.commit();
            return amount;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public void finishOrder(){
    }

    public void clone(int id) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order order = orderDao.getById(session, id);
            assert order != null;
            orderDao.create(session, order.clone());
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() throws Exception {
        List<String> lines = CSVWorker.loadCsvStrings(Order.class);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (Order order: (List<Order>) Parser.parse(Order.class, lines)){
                orderDao.saveOrUpdate(session, order);
            }
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public void exportToCsv() throws Exception {
        CSVWorker.saveToCSV(getAll(null));
    }
}
