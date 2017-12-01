package main;

import beans.*;
import facade.OnlineBookStore;
import repositories.*;
import java.text.ParseException;

public class FileFiller {
    private BookRepository bookRepository = new BookRepository();
    private BookManager bookManager = new BookManager(bookRepository);

    private OrderRepository orderRepository = new OrderRepository();
    private OrderManager orderManager = new OrderManager(orderRepository);

    private RequestRepository requestRepository = new RequestRepository();
    private RequestManager requestManager = new RequestManager(requestRepository, bookRepository);

    public OnlineBookStore fillData() throws ParseException {
        OnlineBookStore store = new OnlineBookStore(bookManager, orderManager, requestManager);
        store.loadAllData();
        return store;
    }



}
