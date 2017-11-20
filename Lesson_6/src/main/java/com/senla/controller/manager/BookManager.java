package com.senla.controller.manager;

import com.senla.entity.Book;
import com.senla.controller.repositories.BookRepository;
import com.senla.util.ArrayWorker;
import com.senla.util.FileWorker;
import com.senla.util.MyProperty;
import com.senla.util.Printer;

import java.util.Comparator;
import java.util.List;

public class BookManager {
    private BookRepository bookRepository = new BookRepository();
    private FileWorker fileWorker = new FileWorker();


    public void saveToFile(){
        fileWorker.save(bookRepository.getBooks(), MyProperty.getMyProperty("bookpath"));
    }

    public void loadFromFile() {
        bookRepository.setBooks((List<Book>)fileWorker.load(MyProperty.getMyProperty("bookpath")));
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
}
