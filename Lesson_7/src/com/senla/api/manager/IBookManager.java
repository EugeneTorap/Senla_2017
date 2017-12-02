package com.senla.api.manager;

import com.senla.model.entity.Book;
import java.util.Comparator;
import java.util.List;

public interface IBookManager extends IManager {
    List<Book> getBooks();
    List<Book> getUnsoldBooks();
    void add(Book newBook);
    void addOnStore(int id);
    void delFromStore(int id);
    void sortBooks(Comparator comparator);
    void sortUnsoldBooks(Comparator comparator);
}
