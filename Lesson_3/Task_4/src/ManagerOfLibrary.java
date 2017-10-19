public class ManagerOfLibrary implements Subscription {

    public void addBook(Library library, Book newBook){
        Book[] books = library.getBooks();
        library.setBooks(addBook(books, newBook));
    }

    private Book[] addBook(Book[] books, Book newBook){
        Book[] newBooks = new Book[books.length + 1];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        newBooks[books.length] = newBook;
        return newBooks;
    }

    private Book[] deleteBook(Book[] books, int Id){
        Book[] newBooks = new Book[books.length - 1];
        int count = 0;
        for (Book book : books) {
            if (Id != book.getId()) {
                newBooks[count] = book;
                count++;
            }
        }
        return newBooks;
    }

    public void addReader(Library library, Reader newReader){
        Reader[] readers = library.getReaders();
        Reader[] newReaders = new Reader[readers.length + 1];
        System.arraycopy(readers, 0, newReaders, 0, readers.length);
        newReaders[readers.length] = newReader;
        library.setReaders(newReaders);
    }

    @Override
    public void subscribeBook(Library library, Reader reader, int Id) {
        Book[] books = library.getBooks();
        Book IdBook = bookSearch(books, Id);

        if (IdBook != null) {
            IdBook.setBusy(false);
            reader.setBooks(addBook(reader.getBooks(), IdBook));
            library.setBooks(deleteBook(books, Id));
            return;
        }
        System.out.println("There's no such book");
    }

    @Override
    public void unSubscribeBook(Library library, Reader reader, int Id) {
        Book[] books = reader.getBooks();
        Book IdBook = bookSearch(books, Id);

        if (IdBook != null) {
            IdBook.setBusy(true);
            addBook(library, IdBook);
            reader.setBooks(deleteBook(books, Id));
            return;
        }
        System.out.println("There's no such book");
    }

    public static Book bookSearch(Book[] books, int Id){
        for (Book book : books) {
            if (Id == book.getId()) {
                return book;
            }
        }
        return null;
    }
}
