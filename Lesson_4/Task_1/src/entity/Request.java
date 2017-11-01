package entity;

public class Request extends Entity {

    private Book book;
    private Reader reader;
    private int amount;

    public Request(Book book, Reader reader, int amount) {
        this.book = book;
        this.reader = reader;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }
}
