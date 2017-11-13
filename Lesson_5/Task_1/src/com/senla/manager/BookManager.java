package com.senla.manager;

import com.senla.entity.Book;
import com.senla.repositories.BookRepository;
import com.senla.util.ArrayWorker;
import com.senla.util.FileWorker;
import com.senla.util.Printer;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

public class BookManager {
    private BookRepository bookRepository = new BookRepository();
    private FileWorker fileWorker = new FileWorker();


    public void saveToFile(){
        fileWorker.save(bookRepository.getBooks(), "book.txt");
    }

    public void loadFromFile() {
        try {
            bookRepository.setBooks(fileWorker.loadBooks("book.txt"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    public void sortBooks(Comparator comparator){
        bookRepository.getBooks().sort(comparator);
    }

    public void sortUnsoldBooks(Comparator comparator){
        bookRepository.getBooksNotSoldMoreSixMonth().sort(comparator);
    }
}
