package com.senla.api.manager;

import com.senla.model.entity.Book;
import java.util.Comparator;
import java.util.List;

public interface IBookManager {
    void add(Book newBook);
    void addOnStore(int id);
    void delFromStore(int id);
    Book findById(int id);
    List<Book> sortBooks(String query, String column);
}
