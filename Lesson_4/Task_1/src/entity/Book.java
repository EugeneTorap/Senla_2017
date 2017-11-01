package entity;

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

    public int getPrice() {
        return price;
    }

    public Date getDatePublished() {
        return datePublished;
    }
}
