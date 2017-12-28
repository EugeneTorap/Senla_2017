package com.senla.model.entity;

import com.senla.annotations.*;
import com.senla.enums.*;
import com.senla.util.IdGenerator;

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


    public Order() {
    }

    public Order(Reader reader,Date dateExecuted, List<Book> books) {
        this.reader = reader;
        this.dateExecuted = dateExecuted;
        this.books = books;
        Date current = new Date();
        if (current.after(this.dateExecuted)) {
            setStatus(Status.EXECUTED);
        }else{
            setStatus(Status.AWAITING);
        }
        calculatePrice();
    }

    public Boolean isExecuted(){
        return status.equals(Status.EXECUTED);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        StringBuilder stringBuilder = new StringBuilder();
        String string = getId() + "," + status + "," + price + "," + df.format(dateExecuted) + "," + reader.getId() +
                "," + books.size() + ",";
        for (Book book: books) {
            stringBuilder.append(book.getId());
            stringBuilder.append(",");
        }
        string += stringBuilder;
        return string;
    }

    public String toStringContents() {
        StringBuilder stringBuilder = new StringBuilder();
        String string = "Order ID" + "," + "Status" + "," + "Price" + "," + "Executed date" + "," + "Reader ID" +
                "," + "Book amount" + ",";
        for (int i = 1; i <= books.size(); i++) {
            stringBuilder.append(i).append(" Book ID,");
        }
        stringBuilder.append("\n");
        string += stringBuilder;
        return string;
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
        clone.setId(IdGenerator.generateId());
        return clone;
    }
}
