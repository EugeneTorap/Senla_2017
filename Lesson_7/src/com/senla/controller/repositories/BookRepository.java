package com.senla.controller.repositories;

import com.senla.api.repository.IBookRepository;
import com.senla.model.entity.Book;
import com.senla.util.ArrayWorker;
import com.senla.util.MyProperty;
import com.senla.util.Printer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookRepository implements IBookRepository {
    private List<Book> books;
    private static BookRepository instance = null;


    private BookRepository() {
        this.books = new ArrayList<>();
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    @Override
    public void add(Book newBook) {
        newBook.setTheBookInStore(false);
        books.add(newBook);
    }

    @Override
    public void addOnStore(int id) {
        changeStatus(id, true);
    }

    @Override
    public void delFromStore(int id) {
        changeStatus(id, false);
    }

    private void changeStatus(int id, Boolean isStore) {
        int index = ArrayWorker.searchIndex(books, id);
        if (index != -1){
            books.get(index).setTheBookInStore(isStore);
            return;
        }
        Printer.print("There's no such book");
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public List<Book> getUnsoldBooks() {
        List<Book> notSoldBooks = new ArrayList<>();
        for (Book book : books) {
            if (isMoreSixMonth(book)) {
                notSoldBooks.add(book);
            }
        }
        return notSoldBooks;
    }

    private Boolean isMoreSixMonth(Book book) {
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.MONTH, (-1) * Integer.parseInt(MyProperty.getInstance().getProperty("amount")));
        return calendar.getTime().after(book.getDateReceipted());
    }
}
