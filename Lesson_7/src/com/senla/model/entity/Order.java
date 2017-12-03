package com.senla.model.entity;

import com.senla.annotations.CsvEntity;
import com.senla.enums.Status;
import com.senla.util.IdGenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CsvEntity(filename = "data/bean.csv", id = "id")
public class Order extends Entity {
    private static final long serialVersionUID = 1242876949608763678L;
    private Reader reader;
    private Status status;
    private Date dateExecuted;
    private List<Book> books;


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
        int price = 0;
        for (Book book: books) {
            price += book.getPrice();
        }
        return price;
    }

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();
        String string = reader.getName() + "," + getId() + "," + getStatus() + "," + getPrice() + "," + df.format(dateExecuted) +
                "," + books.size() + ",";
        for (Book book: books) {
            stringBuilder.append(book.getId());
            stringBuilder.append(",");
        }
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
