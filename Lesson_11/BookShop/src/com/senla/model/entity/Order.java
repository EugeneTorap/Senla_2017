package com.senla.model.entity;

import com.senla.annotations.*;
import com.senla.enums.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CsvEntity(filename = "data/csv/order.csv", id = "order")
public class Order extends Entity {
    private static final long serialVersionUID = 1242876949608763678L;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 1)
    private Reader reader;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 2)
    private Status status;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
    private Date dateExecuted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
    private int price;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 5)
    private List<Book> books;


    public Order(int id, Reader reader, Date dateExecuted, Status status, int price) {
        super(id);
        this.reader = reader;
        this.dateExecuted = dateExecuted;
        this.status = status;
        this.price = price;
    }

    public Order(int id, Reader reader,Date dateExecuted, List<Book> books) {
        super(id);
        this.reader = reader;
        this.dateExecuted = dateExecuted;
        this.books = books;
        Date current = new Date();
        if (current.after(this.dateExecuted)) {
            this.status = Status.EXECUTED;
        }else{
            this.status = Status.AWAITING;
        }
        calculatePrice();
    }

    public Status getStatus() {
        return status;
    }

    public Reader getReader() {
        return reader;
    }

    public int getPrice() {
        return price;
    }

    private void calculatePrice() {
        int price = 0;
        for (Book book: books) {
            price += book.getPrice();
        }
        this.price = price;
    }

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return getId() + "," + status + "," + price + "," + df.format(dateExecuted) + "," + reader.getId();
    }

    private List<Book> cloneList(List<Book> list) throws CloneNotSupportedException {
        List<Book> clone = new ArrayList<>(list.size());
        for (Book item : list) clone.add(item.clone());
        return clone;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        Order clone = (Order) super.clone();
        clone.books = cloneList(books);
        clone.dateExecuted = (Date) dateExecuted.clone();
        clone.reader = reader.clone();
        return clone;
    }
}
