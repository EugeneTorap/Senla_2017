package com.senla.api.dao;

import java.util.List;

public interface IGenericDao<T> {
    void create(T t);

    void saveOrUpdate(T t);

    void delete(T t);

    T getById(int id);

    List<T> getAll(String sort);
}
