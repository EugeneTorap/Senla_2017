package com.senla.api.creator;

import com.senla.api.model.IBook;
import com.senla.api.model.IOrder;
import com.senla.api.model.IReader;
import com.senla.enums.Status;

import java.util.Date;
import java.util.List;

public interface ICreator {
    IBook createBook(int id, String title, int price, boolean isStore, Date datePublished, Date dateReceipted, List<IReader> requests);

    IBook createBook(int id, String title, int price, boolean isStore, Date datePublished, Date dateReceipted);

    IOrder createOrder(int id, IReader reader, Date dateExecuted, List<IBook> books);

    IOrder createOrder(int id, Date dateExecuted, Status status, int price);

    IReader createReader(int id, String name, int balance);

    IReader createReader(int id, String name);
}
