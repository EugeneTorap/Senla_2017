package com.senla.api.manager;

import com.senla.entity.Book;
import java.util.Comparator;

public interface IBookManager extends IManager {
    void showBookInfo(int id);
    void showBooks();
    void showUnsoldBooks();
    void add(Book newBook);
    void addOnStore(int id);
    void delFromStore(int id);
    Book search(int id);
    void sortBooks(Comparator comparator);
    void sortUnsoldBooks(Comparator comparator);
}
