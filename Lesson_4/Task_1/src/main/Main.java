package main;

import beans.BookManager;
import beans.OrderManager;
import beans.RequestManager;
import entity.Book;
import entity.Order;
import entity.Reader;
import entity.Request;
import enums.SortingType;
import enums.Status;
import repositories.BookRepository;
import repositories.OrderRepository;
import repositories.RequestRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {

        Reader r1 = new Reader("Eugene");
        Reader r2 = new Reader("Alla");
        Reader r3 = new Reader("Vlad");
        Reader r4 = new Reader("Ania");

        GregorianCalendar datePub1 = new GregorianCalendar(1999, Calendar.MAY, 31);
        GregorianCalendar datePub2 = new GregorianCalendar(2001, Calendar.DECEMBER, 19);
        GregorianCalendar datePub3 = new GregorianCalendar(1982, Calendar.FEBRUARY, 15);
        GregorianCalendar datePub4 = new GregorianCalendar(2009, Calendar.JULY, 22);
        GregorianCalendar datePub5 = new GregorianCalendar(1967, Calendar.OCTOBER, 11);


        Book b1 = new Book("Night", 5, datePub1);
        Book b2 = new Book("War", 6, datePub2);
        Book b3 = new Book("Rome", 2, datePub3);
        Book b4 = new Book("Space", 9, datePub4);
        Book b5 = new Book("Knight", 7, datePub5);


        Order o1 = new Order(r1, Status.EXECUTED);
        Order o2 = new Order(r2, Status.EXECUTED);
        Order o3 = new Order(r2, Status.EXECUTED);


        Request req1 = new Request(b1, r3, 10);
        Request req2 = new Request(b2, r4, 4);
        Request req3 = new Request(b3, r4, 12);

        BookRepository bookRepository = new BookRepository();
        BookManager bookManager = new BookManager(bookRepository);
        OrderRepository orderRepository = new OrderRepository();
        OrderManager orderManager = new OrderManager(orderRepository);
        RequestRepository requestRepository = new RequestRepository();
        RequestManager requestManager = new RequestManager(requestRepository);
        OnlineBookStore store = new OnlineBookStore(bookManager, orderManager, requestManager);


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

        store.showBookInfo(b1.getId());
        //store.showAllPrice();
        store.showBooksSortedBy(SortingType.DATE);
        //store.showOrdersSortedBy(SortingType.STATUS);
        //store.showRequestsSortedBy(SortingType.ALPHABET);


    }
}
