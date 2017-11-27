package com.senla.controller.manager;

import com.senla.api.manager.IBookManager;
import com.senla.entity.Book;
import com.senla.controller.repositories.BookRepository;
import com.senla.util.*;

import java.io.IOException;
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
    public Book search(int id) {
        return ArrayWorker.search(bookRepository.getBooks(), id);
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
    public void showBookInfo(int id) {
        Book book = search(id);
        if (book != null){
            Printer.print(book);
            return;
        }
        Printer.print("There's no such book");
    }

    @Override
    public void showBooks(){
        Printer.printArray(bookRepository.getBooks());
    }

    @Override
    public void showUnsoldBooks(){
        Printer.printArray(bookRepository.getUnsoldBooks());
    }

    @Override
    public void saveToFile(){
        serializer.save(bookRepository.getBooks(), MyProperty.getInstance().getProperty("bookpath"));
    }

    @Override
    public void loadFromFile() {
        bookRepository.setBooks((List<Book>) serializer.load(MyProperty.getInstance().getProperty("bookpath")));
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
}
