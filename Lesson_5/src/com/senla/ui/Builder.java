package com.senla.ui;

import com.senla.ui.actions.book.*;
import com.senla.ui.actions.order.*;
import com.senla.ui.actions.request.*;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    private Menu rootMenu;


    private Menu buildBookMenu(Menu menu) {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Add book", new AdditionBook()));
        items.add(new MenuItem("Delete book", new DeletingBook()));
        items.add(new MenuItem("Show book info", new BookInfo()));
        items.add(new MenuItem("Show books sorted by alphabet", new SortingBooksByAlphabet()));
        items.add(new MenuItem("Show books sorted by date", new SortingBooksByDate()));
        items.add(new MenuItem("Show books sorted by price", new SortingBooksByPrice()));
        items.add(new MenuItem("Show books sorted by is store", new SortingBooksByStore()));
        items.add(new MenuItem("Show unsold books sorted by date", new SortingUnsoldBooksByDate()));
        items.add(new MenuItem("Show unsold books sorted by price", new SortingUnsoldBooksByPrice()));
        MenuItem begin = new MenuItem("To the beginning");
        begin.setNextMenu(menu);
        items.add(begin);
        return new Menu("BookMenu", items);
    }

    private Menu buildOrderMenu(Menu menu) {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Add order", new AdditionOrder()));
        items.add(new MenuItem("Cancel order", new CancellationOrder()));
        items.add(new MenuItem("Show order info", new BookInfo()));
        items.add(new MenuItem("Show all price", new AllPrice()));
        items.add(new MenuItem("Show amount of executed order", new ExecutedOrderAmount()));
        items.add(new MenuItem("Show orders sorted by date", new SortingOrdersByDate()));
        items.add(new MenuItem("Show orders sorted by price", new SortingOrdersByPrice()));
        items.add(new MenuItem("Show orders sorted by status", new SortingOrdersByStatus()));
        items.add(new MenuItem("Show executed orders sorted by date", new SortingExecutedOrdersByDate()));
        items.add(new MenuItem("Show executed orders sorted by price", new SortingExecutedOrdersByPrice()));
        MenuItem begin = new MenuItem("To the beginning");
        begin.setNextMenu(menu);
        items.add(begin);
        return new Menu("OrderMenu", items);
    }

    private Menu buildRequestMenu(Menu menu) {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Add request", new AdditionRequest()));
        items.add(new MenuItem("Show request sorted by alphabet", new SortingRequestsByAlphabet()));
        items.add(new MenuItem("Show request sorted by date", new SortingRequestsByDate()));
        items.add(new MenuItem("Show request sorted by price", new SortingRequestsByPrice()));
        MenuItem begin = new MenuItem("To the beginning");
        begin.setNextMenu(menu);
        items.add(begin);
        return new Menu("RequestMenu", items);
    }

    public Menu buildMenu() {
        MenuItem bookItem = new MenuItem("books");
        MenuItem orderItem = new MenuItem("orders");
        MenuItem requestItem = new MenuItem("requests");

        List<MenuItem> items = new ArrayList<>();
        items.add(bookItem);
        items.add(orderItem);
        items.add(requestItem);
        items.add(new MenuItem("exit"));

        rootMenu = new Menu("menu", items);
        bookItem.setNextMenu(buildBookMenu(rootMenu));
        orderItem.setNextMenu(buildOrderMenu(rootMenu));
        requestItem.setNextMenu(buildRequestMenu(rootMenu));
        return rootMenu;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
