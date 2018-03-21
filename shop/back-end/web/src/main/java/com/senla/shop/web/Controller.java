package com.senla.shop.web;

import com.google.gson.Gson;
import com.senla.shop.api.facade.IFacade;
import com.senla.shop.enums.SortingType;
import com.senla.shop.model.Book;
import com.senla.shop.model.Order;
import com.senla.shop.model.Reader;
import com.senla.shop.view.Facade;

public class Controller {
    private IFacade facade;
    private static volatile Controller instance;
    private static final Gson GSON = new Gson();


    private Controller(){
        facade = Facade.getInstance();
    }

    public static Controller getInstance() {
        if (instance == null) {
            synchronized (Facade.class) {
                if (instance == null) {
                    instance = new Controller();
                }
            }
        }
        return instance;
    }

    public String getBooksSortedBy(SortingType order) {
        return GSON.toJson(facade.sortBooksBy(order));
    }

    public String getOrdersSortedBy(SortingType order) {
        return GSON.toJson(facade.sortOrdersBy(order));
    }

    public String getReadersSortedBy(SortingType order) {
        return GSON.toJson(facade.sortReadersBy(order));
    }

    public String getBook(Integer id) {
        return GSON.toJson(facade.getBook(id));
    }

    public String getOrder(Integer id) {
        return GSON.toJson(facade.getOrder(id));
    }

    public String getReader(Integer id) {
        return GSON.toJson(facade.getReader(id));
    }

    public void addBook(Book book) {
        facade.addBook(book);
    }

    public void addOrder(Order order) {
        facade.addOrder(order);
    }

    public void addReader(Reader reader) {
        facade.addReader(reader);
    }
}
