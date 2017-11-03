package entity;

import enums.Status;

import java.util.Calendar;
import java.util.Date;

public class Order extends Entity{
    private Reader reader;
    private Status status;
    private Date dateExecuted;
    private Book[] books = new Book[5];

    public Order(Reader reader, Status status) {
        this.reader = reader;
        this.status = status;
    }

    public Boolean isExecuted(){
        return status.equals(Status.EXECUTED);
    }

    public Book[] getBooks() {
        return books;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPrice() {
        int price = 0;
        for (Book book: books) {
            if (book != null) {
                price += book.getPrice();
            }
        }
        return price;
    }

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public Reader getReader() {
        return reader;
    }

    public String toString() {
        return reader.getName() + " " + status;
    }
}
