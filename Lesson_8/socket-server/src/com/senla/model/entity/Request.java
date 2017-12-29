package com.senla.model.entity;

import com.senla.annotations.*;
import com.senla.enums.PropertyType;

@CsvEntity(filename = "data/csv/request.csv", id = "request")
public class Request extends Entity {
    private static final long serialVersionUID = 8836991922455907432L;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 1)
    private Book book;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 2)
    private Reader reader;


    public Request() {
    }

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
        return getId() + "," + reader.getId() + "," + book.getId();// + "," + reader.getName() + "," + book.getTitle();
    }

    public String toStringContents() {
        return "ClientRequest ID" + "," + "Reader ID" + "," + "Book ID" + "\n";// + "," + reader.getName() + "," + book.getTitle();
    }

    public String toStringForRequest() {
        return reader.getName() + ", ID: " + reader.getId();
    }
}
