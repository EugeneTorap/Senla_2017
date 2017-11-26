package com.senla.api.repository;

import com.senla.entity.Book;

import java.util.List;

public interface IBookRepository {
    void add(Book newBook);
    void addOnStore(int id);
    void delFromStore(int id);
    void changeStatus(int id, Boolean isStore);
    List<Book> getBooks();
    void setBooks(List<Book> books);
    List<Book> getUnsoldBooks();
}
