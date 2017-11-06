package beans;

import entity.Book;
import repositories.BookRepository;
import comparator.book.*;
import comparator.book.SortingBooksByDateRec;

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
        Arrays.sort(bookRepository.getBooks(), new SortingBooksByDatePub());
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

    public Book[] sortBooksByAmount(){
        Arrays.sort(bookRepository.getBooks(), new SortingBooksByAmount());
        return bookRepository.getBooks();
    }

    public Book[] sortUnsoldBooksByDate(){
        bookRepository.updateBooksNotSoldMoreSixMonth();
        Arrays.sort(bookRepository.getBooksNotSoldMoreSixMonth(), new SortingBooksByDateRec());
        return bookRepository.getBooksNotSoldMoreSixMonth();
    }

    public Book[] sortUnsoldBooksByPrice(){
        bookRepository.updateBooksNotSoldMoreSixMonth();
        Arrays.sort(bookRepository.getBooksNotSoldMoreSixMonth(), new SortingBooksByPrice());
        return bookRepository.getBooksNotSoldMoreSixMonth();
    }
}
