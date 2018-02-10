package com.senla.api.dao;

import com.senla.model.entity.Book;

public interface IBookDao {
    void create(Book book);
    Book findById(int id);
    void update(int id, boolean status);
}
