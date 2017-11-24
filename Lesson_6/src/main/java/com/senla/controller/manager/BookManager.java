package com.senla.controller.manager;

import com.senla.entity.Book;
import com.senla.controller.repositories.BookRepository;
import com.senla.util.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class BookManager {
    private BookRepository bookRepository;
    private Serializer serializer = new Serializer();

    public BookManager() {
        bookRepository = BookRepository.getInstance();
    }


    public void saveToFile(){
        serializer.save(bookRepository.getBooks(), MyProperty.getInstance().getProperty("bookpath"));
    }

    public void loadFromFile() {
        bookRepository.setBooks((List<Book>) serializer.load(MyProperty.getInstance().getProperty("bookpath")));
    }

    public void showBookInfo(int id) {
        Book book = searchBook(id);
        if (book != null){
            Printer.print(book);
            return;
        }
        System.out.println("There's no such book");
    }

    public void showBooks(){
        Printer.printArray(bookRepository.getBooks());
    }

    public void showUnsoldBooks(){
        Printer.printArray(bookRepository.getBooksNotSoldMoreSixMonth());
    }

    public void addBook(Book newBook){
        bookRepository.addBook(newBook);
    }

    public void addBookOnStore(int id) {
        bookRepository.addBookOnStore(id);
    }

    public void delBookFromStore(int id) {
        bookRepository.delBookFromStore(id);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public Book searchBook(int id){
        return ArrayWorker.search(bookRepository.getBooks(), id);
    }

    public void sortBooks(Comparator comparator){
        bookRepository.getBooks().sort(comparator);
    }

    public void sortUnsoldBooks(Comparator comparator){
        bookRepository.getBooksNotSoldMoreSixMonth().sort(comparator);
    }

    public void exportBook() {
        FileWorker.save(bookRepository.getBooks(), MyProperty.getInstance().getProperty("csvpath"));
    }

    public void importBook() {
        int index;
        for (Book book : Parser.parseBook(FileWorker.load(MyProperty.getInstance().getProperty("csvpath")))) {
            if ((index = ArrayWorker.searchIndex(bookRepository.getBooks(), book.getId())) != -1){
                bookRepository.getBooks().set(index, book);
            } else {
                bookRepository.addBook(book);
            }
        }
    }
}
