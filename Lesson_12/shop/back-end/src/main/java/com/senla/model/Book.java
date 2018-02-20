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
    private Integer price;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
    private Boolean isStore;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
    private Date dateReceipted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
    private Date datePublished;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 7)
    private List<IReader> requests;


    public Book(){

    }

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
    public Boolean getIsStore() {
        return isStore;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getPrice() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setStore(Boolean store) {
        isStore = store;
    }

    public void setDateReceipted(Date dateReceipted) {
        this.dateReceipted = dateReceipted;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public List<IReader> getRequests() {
        return requests;
    }

    public void setRequests(List<IReader> requests) {
        this.requests = requests;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book that = (Book) o;

        if (id != that.id) return false;
        if (isStore != that.isStore) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (dateReceipted != null ? !dateReceipted.equals(that.dateReceipted) : that.dateReceipted != null)
            return false;
        if (datePublished != null ? !datePublished.equals(that.datePublished) : that.datePublished != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isStore ? 1 : 0);
        result = 31 * result + (dateReceipted != null ? dateReceipted.hashCode() : 0);
        result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
