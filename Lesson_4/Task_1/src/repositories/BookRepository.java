package repositories;

import util.Checker;
import entity.Book;

public class BookRepository {
    private Book[] books = new Book[5];

    public Book[] getBooks() {
        return books;
    }

    public void addBook(Book newBook){
        if (Checker.getPosition(books) != -1) {
            int position = Checker.getPosition(books);
            books[position] = newBook;
            return;
        }
        System.out.println("Book repository is full");
    }

    public void addBookOnStore(int id){
        Book idBook = Checker.search(books, id);

        if (idBook != null){
            idBook.setTheBookInStore(true);
            changeBook(idBook);
            return;
        }
        System.out.println("There's no such book");
    }

    public void delBookFromStore(int id){
        Book idBook = Checker.search(books, id);

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
