package manager;

import entity.Book;
import repositories.BookRepository;
import util.ArrayWorker;
import util.FileWorker;
import util.Printer;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

public class BookManager {
    private BookRepository bookRepository = new BookRepository();
    private FileWorker fileWorker = new FileWorker("book.txt", "reader.txt",
            "order.txt", "request.txt");


    public void saveToFile(){
        fileWorker.saveBooks(bookRepository.getBooks());
    }

    public void loadFromFile() throws ParseException {
        bookRepository.setBooks(fileWorker.loadBooks());
    }

    public void showBookInfo(int id) {
        Printer.print(ArrayWorker.searchBook(bookRepository.getBooks(), id));
    }

    public void showBooks(){
        Printer.printBooks(bookRepository.getBooks());
    }

    public void showUnsoldBooks(){
        Printer.printBooks(bookRepository.getBooksNotSoldMoreSixMonth());
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
