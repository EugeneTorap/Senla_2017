package com.senla.model;

import com.senla.annotations.*;
import com.senla.api.model.IBook;
import com.senla.api.model.IReader;
import com.senla.enums.PropertyType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CsvEntity(filename = "data/csv/book.csv", id = "book")
public class Book extends Entity implements IBook {
    private static final long serialVersionUID = 5271189679230904618L;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
    private String title;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
    private int price;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
    private Boolean isStore;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
    private Date dateReceipted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
    private Date datePublished;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 7)
    private List<IReader> requests;


    public Book(int id, String title, int price, boolean isStore, Date datePublished, Date dateReceipted, List<IReader> requests) {
        super(id);
        this.title = title;
        this.price = price;
        this.isStore = isStore;
        this.datePublished = datePublished;
        this.dateReceipted = dateReceipted;
        this.requests = requests;
    }

    public Book(int id, String title, int price, boolean isStore, Date datePublished, Date dateReceipted) {
        super(id);
        this.title = title;
        this.price = price;
        this.isStore = isStore;
        this.datePublished = datePublished;
        this.dateReceipted = dateReceipted;
    }

    @Override
    public Boolean getTheBookInStore() {
        return isStore;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public Date getDatePublished() {
        return datePublished;
    }

    @Override
    public Date getDateReceipted() {
        return dateReceipted;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return title + "," + getId() + "," + price + "," + isStore + "," + df.format(datePublished) +
                "," + df.format(dateReceipted);
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }
}
