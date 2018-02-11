package com.senla.api.dao;

import com.senla.model.entity.Book;
import com.senla.model.entity.Reader;

public interface IBookDao {
    void create(Book book);
    void create(Book book, Reader reader);
    Book findById(int id);
    void update(int id, boolean status);
}
