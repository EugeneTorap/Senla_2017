package com.senla.api.dao;

import com.senla.dao.DaoException;

import java.util.List;

public interface IGenericDao<T> {
    void create(T t) throws Exception;

    void update(int id) throws Exception;

    void delete(int id) throws Exception;

    T getById(int id) throws Exception;

    List<T> getAll(String sort) throws Exception;
}
