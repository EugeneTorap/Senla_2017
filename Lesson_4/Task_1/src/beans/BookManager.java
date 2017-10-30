package beans;

import entity.Book;
import repositories.BookRepository;
import util.sorter.booksorter.*;

import java.util.Arrays;

public class BookManager {
    private BookRepository bookRepository;

    public BookManager(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public Book[] sortBooksByAlphabet(){
        Arrays.sort(bookRepository.getBooks(), new SortingBooksByAlphabet());
        return bookRepository.getBooks();
    }

    public Book[] sortBooksByDate(){
        Arrays.sort(bookRepository.getBooks(), new SortingBooksByDate());
        return bookRepository.getBooks();
    }

    public Book[] sortBooksByPrice(){
        Arrays.sort(bookRepository.getBooks(), new SortingBooksByPrice());
        return bookRepository.getBooks();
    }

    public Book[] sortBooksByStore(){
        Arrays.sort(bookRepository.getBooks(), new SortingBooksByStore());
        return bookRepository.getBooks();
    }
}
