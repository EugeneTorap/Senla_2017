package entity;

import enums.Status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order extends Entity{
    private Reader reader;
    private Status status;
    private Date dateExecuted;
    private Book[] books;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public Order(Reader reader,Date dateExecuted, Book[] books) {
        this.reader = reader;
        this.dateExecuted = dateExecuted;
        this.books = books;
        Date current = new Date();
        if (current.after(this.dateExecuted)) {
            setStatus(Status.EXECUTED);
        }else{
            setStatus(Status.AWAITING);
        }
    }

    public Boolean isExecuted(){
        return status.equals(Status.EXECUTED);
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
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

    public void setDateExecuted(Date dateExecuted) {
        this.dateExecuted = dateExecuted;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String string = reader.getName() + " " + getId() + " " + getStatus() + " " + getPrice() + " " + df.format(dateExecuted) +
                " " + books.length + " ";
        for (Book book: books) {
            stringBuilder.append(book.getId());
            stringBuilder.append(" ");
        }
        string += stringBuilder;
        return string;
    }
}
