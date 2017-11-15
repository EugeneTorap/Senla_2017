package com.senla.manager;

import com.senla.entity.Book;
import com.senla.repositories.BookRepository;
import com.senla.util.ArrayWorker;
import com.senla.util.FileWorker;
import com.senla.util.Printer;

import java.util.Comparator;

public class BookManager {
    private BookRepository bookRepository = new BookRepository();
    private FileWorker fileWorker = new FileWorker();


    public void saveToFile(){
        fileWorker.save(bookRepository.getBooks(), "data/book.txt");
    }

    public void loadFromFile() {
        bookRepository.setBooks(fileWorker.loadBooks("data/book.txt"));
    }

    public void showBookInfo(int id) {
        Printer.print(ArrayWorker.searchBook(bookRepository.getBooks(), id));
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
        return ArrayWorker.searchBook(bookRepository.getBooks(), id);
    }

    public void sortBooks(Comparator comparator){
        bookRepository.getBooks().sort(comparator);
    }

    public void sortUnsoldBooks(Comparator comparator){
        bookRepository.getBooksNotSoldMoreSixMonth().sort(comparator);
    }
}
