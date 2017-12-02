package com.senla.api.repository;

import com.senla.model.entity.Book;

import java.util.List;

public interface IBookRepository {
    void add(Book newBook);
    void addOnStore(int id);
    void delFromStore(int id);
    List<Book> getBooks();
    void setBooks(List<Book> books);
    List<Book> getUnsoldBooks();
}
