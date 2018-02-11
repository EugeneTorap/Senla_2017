package com.senla.api.manager;

import com.senla.model.entity.Book;
import com.senla.model.entity.Reader;

import java.util.Comparator;
import java.util.List;

public interface IBookManager {
    void add(Book newBook);
    void add(Book book, Reader reader);
    void addOnStore(int id);
    void delFromStore(int id);
    Book findById(int id);
    List<Book> sortBooks(String query, String column);
}
