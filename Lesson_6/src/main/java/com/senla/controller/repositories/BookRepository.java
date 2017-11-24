package com.senla.controller.repositories;

import com.senla.entity.Book;
import com.senla.util.ArrayWorker;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooksNotSoldMoreSixMonth(){
        List<Book> notSoldBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getIsMoreSixMonth()) {
                notSoldBooks.add(book);
            }
        }
        return notSoldBooks;
    }

    public void addBook(Book newBook){
        newBook.setTheBookInStore(false);
        books.add(newBook);
    }

    public void addBookOnStore(int id){
        changeBook(id, true);
    }

    public void delBookFromStore(int id){
        changeBook(id, false);
    }

    private void changeBook(int id, Boolean isStore){
        int index = ArrayWorker.searchIndex(books, id);
        if (index != -1){
            books.get(index).setTheBookInStore(isStore);
            return;
        }
        System.out.println("There's no such book");
    }
}
