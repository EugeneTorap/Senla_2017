package entity;

import java.util.Date;

public class Book extends Entity {
    private String title;
    private int price;
    private Boolean isTheBookInStore;
    private Date datePublished;
    private Date dateReceipted;

    public Book(int id, String title, int price) {
        super(id);
        this.title = title;
        this.price = price;
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
