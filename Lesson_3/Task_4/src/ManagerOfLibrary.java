public class ManagerOfLibrary implements Subscription {

    private Book[] libraryBooks;
    private Reader[] readers;

    public ManagerOfLibrary(int amountOfBook, int amountOfReader) {
        this.libraryBooks = new Book[amountOfBook];
        this.readers = new Reader[amountOfReader];
    }

    public void setBooks(Book[] libraryBooks) {
        this.libraryBooks = libraryBooks;
    }

    public void setReaders(Reader[] readers) {
        this.readers = readers;
    }

    public void addBook(Book newBook){
        libraryBooks = addBook(libraryBooks, newBook);
    }

    private Book[] addBook(Book[] books, Book newBook){
        Book[] newBooks = new Book[books.length + 1];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        newBooks[books.length] = newBook;
        return newBooks;
    }

    private Book[] deleteBook(Book[] books, int id){
        Book[] newBooks = new Book[books.length - 1];
        int count = 0;
        for (Book book : books) {
            if (id != book.getId()) {
                newBooks[count] = book;
                count++;
            }
        }
        return newBooks;
    }

    public void addReader(Reader newReader){
        Reader[] newReaders = new Reader[readers.length + 1];
        System.arraycopy(readers, 0, newReaders, 0, readers.length);
        newReaders[readers.length] = newReader;
        readers = newReaders;
    }

    public void showAllReader(){
        System.out.println("\nList of all readers: \n");
        for (Reader i: readers) {
            System.out.println(i.getName());
        }
    }

    public void showAllBooks(){
        System.out.println("\nList of all books: \n");
        for (Book i: libraryBooks) {
            System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle() + ", " + "in library");
        }
        for (Reader j: readers) {
            for (Book i: j.getBooks()) {
                System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle() + ", " + "in Reader");
            }
        }
    }

    public void showAllBooksOfReader(Reader reader){
        System.out.println("\nList of all books of Reader: \n");
        for (Book i: reader.getBooks()) {
            System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle());
        }
    }

    public void showReader(int id){
        for (Reader i : readers) {
            if (bookSearch(i.getBooks(), id) != null) {
                System.out.println("\n" + i.getName() + " has that book \n");
                return;
            }
        }
        System.out.println("There's no reader with such book");
    }

    @Override
    public void subscribeBook(Reader reader, int id) {
        Book idBook = bookSearch(libraryBooks, id);

        if (idBook != null) {
            idBook.setBusy(false);
            reader.setBooks(addBook(reader.getBooks(), idBook));
            libraryBooks = deleteBook(libraryBooks, id);
            return;
        }
        System.out.println("There's no such book");
    }

    @Override
    public void unSubscribeBook(Reader reader, int id) {
        Book[] books = reader.getBooks();
        Book idBook = bookSearch(books, id);

        if (idBook != null) {
            idBook.setBusy(true);
            addBook(idBook);
            reader.setBooks(deleteBook(books, id));
            return;
        }
        System.out.println("There's no such book");
    }

    public Book bookSearch(Book[] books, int id){
        for (Book book : books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }
}
