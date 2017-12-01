public class ManagerOfLibrary implements Subscription {

    final String finalLibrary = "inLibrary";
    final String finalReader = "reader's";

    private Book[] books;
    private Reader[] readers;

    public ManagerOfLibrary(int amountOfBook, int amountOfReader) {
        this.books = new Book[amountOfBook];
        this.readers = new Reader[amountOfReader];
    }

    public void addBookInLibrary(Book newBook){
        if (Checker.getPosition(books) != -1) {
            newBook.setStatus(finalLibrary);
            int position = Checker.getPosition(books);
            books[position] = newBook;
            return;
        }
        System.out.println("Library is full");
    }

    public void addBookToReader(Book newBook, Reader reader){
        if (Checker.getPosition(reader.getBooks()) != -1) {
            newBook.setStatus(finalReader);
            int position = Checker.getPosition(reader.getBooks());
            reader.getBooks()[position] = newBook;
            return;
        }
        System.out.println("Reader's books is full");
    }

    public void addReader(Reader newReader){
        if (Checker.getPosition(readers) != -1) {
            int position = Checker.getPosition(readers);
            readers[position] = newReader;
            return;
        }
        System.out.println("Reader's books is full");
    }

    public void showAllReader(){
        System.out.println("\nList of all readers: \n");
        for (Reader i: readers) {
            System.out.println(i.getName());
        }
    }

    public void showAllBooks(){
        System.out.println("\nList of all books: \n");
        for (Book i: books) {
            System.out.print("Id: " + i.getId() + ", Book: " + i.getTitle() + ", ");
            if (i.getStatus().equals(finalLibrary)) {
                System.out.println("in library");
            } else {
                System.out.println("reader's");
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
        Book idBook = bookSearch(books, id);

        if (idBook != null) {
            if (idBook.getStatus().equals(finalLibrary)) {
                addBookToReader(idBook, reader);
                return;
            }
        }
        System.out.println("There's no such book");
    }

    @Override
    public void unSubscribeBook(Reader reader, int id) {
        Book[] books = reader.getBooks();
        Book idBook = bookSearch(books, id);

        if (idBook != null) {
            for (int i = 0; i < books.length; i++) {
                if (books[i].equals(idBook)) {
                    reader.getBooks()[i] = null;
                }
            }
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
