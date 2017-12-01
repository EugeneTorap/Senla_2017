package entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book extends Entity {
    private String title;
    private int price;
    private Boolean isTheBookInStore;
    private Date datePublished;
    private Date dateReceipted;
    private int requestAmount;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");


    public Book(String title, int price, Date datePublished, Date dateReceipted) {
        this.title = title;
        this.price = price;
        this.datePublished = datePublished;
        this.dateReceipted = dateReceipted;
    }

    public Boolean getTheBookInStore() {
        return isTheBookInStore;
    }

    public Boolean getIsMoreSixMonth() {
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.MONTH, -6);
        return calendar.getTime().after(this.dateReceipted);
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

    public Date getDateReceipted() {
        return dateReceipted;
    }

    public void setDateReceipted(Date dateReceipted) {
        this.dateReceipted = dateReceipted;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String toString() {
        return title + " " + getId() + " " + price + " " + isTheBookInStore + " " + df.format(datePublished) +
                " " + df.format(dateReceipted);
    }
}
