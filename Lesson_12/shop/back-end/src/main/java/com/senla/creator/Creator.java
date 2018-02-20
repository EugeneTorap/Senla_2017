package com.senla.creator;

import com.senla.api.creator.ICreator;
import com.senla.api.model.*;
import com.senla.enums.Status;
import com.senla.model.*;

import java.util.Date;
import java.util.List;

public class Creator implements ICreator {

    @Override
    public IBook createBook(int id, String title, int price, boolean isStore, Date datePublished,
                                Date dateReceipted, List<IReader> requests) {
        return new Book(id, title, price, isStore, datePublished, dateReceipted, requests);
    }

    @Override
    public IBook createBook(int id, String title, int price, boolean isStore, Date datePublished, Date dateReceipted) {
        return new Book(id, title, price, isStore, datePublished, dateReceipted);
    }

    @Override
    public IOrder createOrder(int id, IReader reader,Date dateExecuted, List<IBook> books) {
        return new Order(id, reader,dateExecuted, books);
    }

    @Override
    public IOrder createOrder(int id, Date dateExecuted, Status status, int price) {
        return new Order(id, dateExecuted, status, price);
    }

    @Override
    public IReader createReader(int id, String name, int balance) {
        return new Reader(id, name, balance);
    }

    @Override
    public IReader createReader(int id, String name) {
        return new Reader(id, name);
    }
}
