package com.senla.api.dao;

import com.senla.controller.dao.DaoException;

import java.util.List;

public interface GenericDao<T> {
    void create(T t) throws DaoException;

    void update(int id) throws DaoException;

    void delete(int id) throws DaoException;

    T getById(int id) throws DaoException;

    List<T> getAll(String sort) throws DaoException;
}
