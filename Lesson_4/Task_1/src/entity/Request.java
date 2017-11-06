package entity;

public class Request extends Entity {

    private Book book;
    private Reader reader;

    public Request(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public String toString() {
        return getId() + " " + reader.getName() + " " + book.getTitle();
    }
}
