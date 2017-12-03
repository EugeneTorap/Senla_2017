package com.senla.model.entity;

import com.senla.annotations.CsvEntity;

@CsvEntity(filename = "data/bean.csv", id = "id")
public class Request extends Entity {
    private static final long serialVersionUID = 8836991922455907432L;
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
        return getId() + "," + reader.getId() + "," + book.getId() + "," + reader.getName() + "," + book.getTitle();
    }

    public String toStringForRequest() {
        return reader.getName() + ", ID: " + reader.getId();
    }
}
