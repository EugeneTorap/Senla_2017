package repositories;

import util.ArrayWorker;
import util.Checker;
import entity.Book;

public class BookRepository {
    private Book[] books = new Book[50];
    private Book[] notSoldBooks;

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Book[] getBooksNotSoldMoreSixMonth(){
        return notSoldBooks;
    }

    public void updateBooksNotSoldMoreSixMonth(){
        int count = 0;
        for (Book book : books) {
            if (book != null && book.getIsMoreSixMonth()) {
                count++;
            }
        }
        Book[] notSoldBooks = new Book[count];

        for (Book book : books) {
            if (Checker.getPosition(notSoldBooks) != -1 && book != null && book.getIsMoreSixMonth()) {
                int position = Checker.getPosition(notSoldBooks);
                notSoldBooks[position] = book;
            }
        }
        this.notSoldBooks = notSoldBooks;
    }

    public void addBook(Book newBook){
        if (Checker.getPosition(books) == -1) {
            books = ArrayWorker.extendArray(books);
        }
        newBook.setTheBookInStore(false);
        int position = Checker.getPosition(books);
        books[position] = newBook;
    }

    public void addBookOnStore(int id){
        Book idBook = ArrayWorker.search(books, id);

        if (idBook != null){
            idBook.setTheBookInStore(true);
            changeBook(idBook);
            return;
        }
        System.out.println("There's no such book");
    }

    public void delBookFromStore(int id){
        Book idBook = ArrayWorker.search(books, id);

        if (idBook != null){
            idBook.setTheBookInStore(false);
            changeBook(idBook);
            return;
        }
        System.out.println("There's no such book");
    }

    private void changeBook(Book idBook){
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == idBook.getId()) {
                books[i] = idBook;
                return;
            }
        }
    }
}
