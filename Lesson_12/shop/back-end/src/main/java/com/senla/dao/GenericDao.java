package com.senla.dao;

import com.senla.api.dao.IGenericDao;
import com.senla.executor.Executor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.List;

public abstract class GenericDao<T> implements IGenericDao<T> {

    private Class<?> clazz;

    protected GenericDao(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public void create(T t) {
        Executor.transact(session -> {
            session.save(t);
            return null;
        });
    }

    @Override
    public void saveOrUpdate(T t) {
        Executor.transact(session -> {
            session.saveOrUpdate(t);
            return null;
        });
    }

    @Override
    public void delete(T t) {
        Executor.transact(session -> {
            session.delete(t);
            return null;
    });
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(int id) {
        return Executor.transact(session -> (T) session.load(clazz, id));
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<T> getAll(String sort) {
        return Executor.transact(session -> {
            Criteria criteria = session.createCriteria(clazz);
            return criteria.addOrder(Order.asc(sort)).list();
        });
    }
}
