package entity;

public class Request extends Entity {

    private Book book;
    private Reader reader;
    private int amount;

    public Request(int id, Reader reader) {
        super(id);
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }
}
