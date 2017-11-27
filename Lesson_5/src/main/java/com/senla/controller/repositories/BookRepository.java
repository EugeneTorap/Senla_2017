package com.senla.controller.repositories;

import com.senla.api.repository.IBookRepository;
import com.senla.entity.Book;
import com.senla.util.ArrayWorker;

import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private List<Book> books = new ArrayList<>();
    private static volatile BookRepository instance = null;


    public static BookRepository getInstance() {
        if (instance == null) {
            synchronized (BookRepository.class){
                if (instance == null) {
                    instance = new BookRepository();
                }
            }
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

    @Override
    public void changeStatus(int id, Boolean isStore) {
        int index = ArrayWorker.searchIndex(books, id);
        if (index != -1){
            books.get(index).setTheBookInStore(isStore);
            return;
        }
        System.out.println("There's no such book");
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
            if (book.isMoreSixMonth()) {
                notSoldBooks.add(book);
            }
        }
        return notSoldBooks;
    }
}
