package com.senla.controller.manager;

import com.senla.api.manager.IBookManager;
import com.senla.model.entity.Book;
import com.senla.controller.repositories.BookRepository;
import com.senla.util.*;

import java.util.Comparator;
import java.util.List;

public class BookManager implements IBookManager {
    private BookRepository bookRepository;
    private Serializer serializer = new Serializer();


    public BookManager() {
        bookRepository = BookRepository.getInstance();
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
    public void exportToFile() {
        FileWorker.save(bookRepository.getBooks(), MyProperty.getInstance().getProperty("csvpath"));
    }

    @Override
    public void importFromFile() {
        int index;
        for (Book book : Parser.parseBook(FileWorker.load(MyProperty.getInstance().getProperty("csvpath")))) {
            if ((index = ArrayWorker.searchIndex(bookRepository.getBooks(), book.getId())) != -1){
                bookRepository.getBooks().set(index, book);
            } else {
                bookRepository.add(book);
            }
        }
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
