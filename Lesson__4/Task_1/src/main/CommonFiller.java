package main;

import beans.BookManager;
import beans.OrderManager;
import beans.RequestManager;
import entity.Book;
import entity.Order;
import entity.Reader;
import entity.Request;
import facade.OnlineBookStore;
import repositories.BookRepository;
import repositories.OrderRepository;
import repositories.RequestRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFiller {
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private Reader r1 = new Reader("Eugene");
    private Reader r2 = new Reader("Alla");
    private Reader r3 = new Reader("Vlad");
    private Reader r4 = new Reader("Ania");

    private Date datePub1 = df.parse("31/06/1999");
    private Date datePub2 = df.parse("19/04/1987");
    private Date datePub3 = df.parse("16/03/1998");
    private Date datePub4 = df.parse("22/07/2006");
    private Date datePub5 = df.parse("06/08/2001");

    private Date dateRec1 = df.parse("30/06/2014");
    private Date dateRec2 = df.parse("15/04/2015");
    private Date dateRec3 = df.parse("10/03/2017");
    private Date dateRec4 = df.parse("19/07/2016");
    private Date dateRec5 = df.parse("09/08/2015");

    private Date dateEx1 = df.parse("29/05/2017");
    private Date dateEx2 = df.parse("07/04/2014");
    private Date dateEx3 = df.parse("23/07/2018");


    private Book b1 = new Book("Night", 5, datePub1, dateRec1);
    private Book b2 = new Book("War", 6, datePub2, dateRec2);
    private Book b3 = new Book("Rome", 2, datePub3, dateRec3);
    private Book b4 = new Book("Space", 9, datePub4, dateRec4);
    private Book b5 = new Book("Knight", 7, datePub5, dateRec5);

    private Book[] books1 = {b1, b3, b5};
    private Book[] books2 = {b2, b4};
    private Book[] books3 = {b1, b3, b4, b5};

    private Order o1 = new Order(r1, dateEx1, books1);
    private Order o2 = new Order(r2, dateEx2, books2);
    private Order o3 = new Order(r2, dateEx3, books3);


    private Request req1 = new Request(b1, r3);
    private Request req2 = new Request(b2, r4);
    private Request req3 = new Request(b2, r4);
    private Request req4 = new Request(b2, r3);
    private Request req5 = new Request(b1, r3);

    private BookRepository bookRepository = new BookRepository();
    private BookManager bookManager = new BookManager(bookRepository);

    private OrderRepository orderRepository = new OrderRepository();
    private OrderManager orderManager = new OrderManager(orderRepository);

    private RequestRepository requestRepository = new RequestRepository();
    private RequestManager requestManager = new RequestManager(requestRepository, bookRepository);

    public CommonFiller() throws ParseException {
    }

    public OnlineBookStore fillData() throws ParseException {
        OnlineBookStore store = new OnlineBookStore(bookManager, orderManager, requestManager);
        store.addReader(r1);
        store.addReader(r2);
        store.addReader(r3);
        store.addReader(r4);

        store.addBook(b1);
        store.addBook(b2);
        store.addBook(b3);
        store.addBook(b4);
        store.addBook(b5);

        store.addBookOnStore(b1);
        store.addBookOnStore(b2);

        store.addOrder(o1);
        store.addOrder(o2);
        store.addOrder(o3);

        store.addRequest(req1);
        store.addRequest(req2);
        store.addRequest(req3);
        store.addRequest(req4);
        store.addRequest(req5);

        return store;
    }
}
