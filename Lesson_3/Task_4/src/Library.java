public class Library {

    private Book[] books;
    private Reader[] readers;

    public Library(int amountOfBook, int amountOfReader) {
        this.books = new Book[amountOfBook];
        this.readers = new Reader[amountOfReader];
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Reader[] getReaders() {
        return readers;
    }

    public void setReaders(Reader[] readers) {
        this.readers = readers;
    }
}
