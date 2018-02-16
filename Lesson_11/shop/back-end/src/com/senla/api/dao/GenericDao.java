package com.senla.api.dao;

import java.util.List;

public interface GenericDao<T> {
    void create(T t);

    void update(int id);

    void delete(int id);

    T getById(int id);

    List<T> getAll(String sort);
}
