package com.senla.model;

import com.senla.annotations.*;
import com.senla.api.model.IBook;
import com.senla.api.model.IOrder;
import com.senla.api.model.IReader;
import com.senla.enums.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CsvEntity(filename = "data/csv/order.csv", id = "order")
public class Order extends Entity implements IOrder {
    private static final long serialVersionUID = 1242876949608763678L;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 1)
    private IReader reader;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 2)
    private Status status;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
    private Date dateExecuted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
    private Integer price;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 5)
    private List<IBook> books;


    public Order(){

    }

    public Order(int id, Date dateExecuted, Status status, int price) {
        super(id);
        this.dateExecuted = dateExecuted;
        this.status = status;
        this.price = price;
    }

    public Order(int id, IReader reader, Date dateExecuted, List<IBook> books) {
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

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public IReader getReader() {
        return reader;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    private void calculatePrice() {
        int price = 0;
        for (IBook book: books) {
            price += book.getPrice();
        }
        this.price = price;
    }

    @Override
    public Date getDateExecuted() {
        return dateExecuted;
    }

    public void setReader(IReader reader) {
        this.reader = reader;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDateExecuted(Date dateExecuted) {
        this.dateExecuted = dateExecuted;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<IBook> getBooks() {
        return books;
    }

    public void setBooks(List<IBook> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return getId() + "," + status + "," + price + "," + df.format(dateExecuted);
    }

    private List<IBook> cloneList(List<IBook> list) throws CloneNotSupportedException {
        List<IBook> clone = new ArrayList<>(list.size());
        for (IBook item : list) clone.add(item.clone());
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
