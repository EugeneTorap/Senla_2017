package com.senla.model.entity;

import com.senla.annotations.CsvEntity;
import com.senla.annotations.CsvProperty;
import com.senla.enums.PropertyType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CsvEntity(filename = "data/csv/book.csv", id = "book")
public class Book extends Entity {
    private static final long serialVersionUID = 5271189679230904618L;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
    private String title;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
    private int price;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
    private Boolean isTheBookInStore;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
    private Date dateReceipted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
    private Date datePublished;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 7)
    private List<Reader> requests;


    public Book(int id, String title, int price, boolean isTheBookInStore, Date datePublished, Date dateReceipted, List<Reader> requests) {
        super(id);
        this.title = title;
        this.price = price;
        this.isTheBookInStore = isTheBookInStore;
        this.datePublished = datePublished;
        this.dateReceipted = dateReceipted;
        this.requests = requests;
    }

    public Book(int id, String title, int price, boolean isTheBookInStore, Date datePublished, Date dateReceipted) {
        super(id);
        this.title = title;
        this.price = price;
        this.isTheBookInStore = isTheBookInStore;
        this.datePublished = datePublished;
        this.dateReceipted = dateReceipted;
    }

    public Boolean getTheBookInStore() {
        return isTheBookInStore;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public Date getDateReceipted() {
        return dateReceipted;
    }

    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return title + "," + getId() + "," + price + "," + isTheBookInStore + "," + df.format(datePublished) +
                "," + df.format(dateReceipted);
    }

    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }
}
