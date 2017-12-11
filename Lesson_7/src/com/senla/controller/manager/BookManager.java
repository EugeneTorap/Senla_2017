package com.senla.controller.manager;

import com.senla.api.manager.IBookManager;
import com.senla.csv.Parser;
import com.senla.dependencyinjection.DependencyInjection;
import com.senla.model.entity.Book;
import com.senla.controller.repositories.BookRepository;
import com.senla.util.*;

import java.util.Comparator;
import java.util.List;

public class BookManager implements IBookManager {
    private BookRepository bookRepository;
    private Serializer serializer = new Serializer();


    public BookManager() {
        bookRepository = (BookRepository) DependencyInjection.getInstance().getObject(BookRepository.class);
    }

    @Override
    public void add(Book newBook) {
        bookRepository.add(newBook);
    }

    @Override
    public void addOnStore(int id) {
        bookRepository.addOnStore(id);
    }

    @Override
    public void delFromStore(int id) {
        bookRepository.delFromStore(id);
    }

    @Override
    public void sortBooks(Comparator comparator){
        bookRepository.getBooks().sort(comparator);
    }

    @Override
    public void sortUnsoldBooks(Comparator comparator){
        bookRepository.getUnsoldBooks().sort(comparator);
    }

    @Override
    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    @Override
    public List<Book> getUnsoldBooks(){
        return bookRepository.getUnsoldBooks();
    }

    @Override
    public void serialize(){
        serializer.save(bookRepository.getBooks(), MyProperty.getInstance().getProperty("bookpath"));
    }

    @Override
    public void deserialize() {
        bookRepository.setBooks((List<Book>) serializer.load(MyProperty.getInstance().getProperty("bookpath")));
    }
}
