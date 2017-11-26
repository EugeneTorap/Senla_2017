package com.senla.entity;

import com.senla.util.MyProperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Book extends Entity {
    private static final long serialVersionUID = 5271189679230904618L;
    private String title;
    private int price;
    private Boolean isTheBookInStore;
    private Date datePublished;
    private Date dateReceipted;
    private int requestAmount;


    public Book(String title, int price, Date datePublished, Date dateReceipted) {
        this.title = title;
        this.price = price;
        this.datePublished = datePublished;
        this.dateReceipted = dateReceipted;
    }

    public Boolean getTheBookInStore() {
        return isTheBookInStore;
    }

    public Boolean isMoreSixMonth() {
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.MONTH, (-1) * Integer.parseInt(MyProperty.getInstance().getProperty("amount")));
        return calendar.getTime().after(this.dateReceipted);
    }

    public void setTheBookInStore(Boolean theBookInStore) {
        isTheBookInStore = theBookInStore;
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

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return title + " " + getId() + " " + price + " " + isTheBookInStore + " " + df.format(datePublished) +
                " " + df.format(dateReceipted);
    }

    public String toStringWithSign() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return title + "," + getId() + "," + price + "," + isTheBookInStore + "," + df.format(datePublished) +
                "," + df.format(dateReceipted);
    }

    public String toStringForRequest() {
        return "\t\t" + title + ": " + requestAmount;
    }

    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }
}
