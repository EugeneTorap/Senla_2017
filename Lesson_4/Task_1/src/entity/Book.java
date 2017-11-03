package entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Book extends Entity {
    private String title;
    private int price;
    private Boolean isTheBookInStore;
    private GregorianCalendar datePub;
    //private GregorianCalendar dateRec;
    private Date datePublished;
    //private Date dateReceipted;

    public Book(String title, int price, GregorianCalendar datePub) {
        this.title = title;
        this.price = price;
        this.datePub = datePub;
        datePublished = datePub.getTime();
    }


    public Boolean getTheBookInStore() {
        return isTheBookInStore;
    }

    public void setTheBookInStore(Boolean theBookInStore) {
        isTheBookInStore = theBookInStore;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(GregorianCalendar datePublished) {
        this.datePublished = datePublished.getTime();
    }

    public String toString() {
        return title + " " + getId() + " " + price + " " + isTheBookInStore + " " + datePub.get(Calendar.DATE);
    }
}
