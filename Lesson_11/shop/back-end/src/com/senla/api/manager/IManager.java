package com.senla.api.manager;

import java.util.List;

public interface IManager<T> {
    void create(T t);

    void delete(int id);

    T getById(int id);

    List<T> getAll(String sort);

    String importToCsv();

    String exportFromCsv();
}
