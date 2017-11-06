package main;

import beans.*;
import entity.*;
import repositories.*;
import util.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        FileWorker fileWorker = new FileWorker("book.txt", "reader.txt", "order.txt");

        Reader r1 = new Reader("Eugene");
        Reader r2 = new Reader("Alla");
        Reader r3 = new Reader("Vlad");
        Reader r4 = new Reader("Ania");

        Date datePub1 = df.parse("31/06/1999");
        Date datePub2 = df.parse("19/04/1987");
        Date datePub3 = df.parse("16/03/1998");
        Date datePub4 = df.parse("22/07/2006");
        Date datePub5 = df.parse("06/08/2001");

        Date dateRec1 = df.parse("30/06/2014");
        Date dateRec2 = df.parse("15/04/2015");
        Date dateRec3 = df.parse("10/03/2017");
        Date dateRec4 = df.parse("19/07/2016");
        Date dateRec5 = df.parse("09/08/2015");

        Date dateEx1 = df.parse("29/05/2017");
        Date dateEx2 = df.parse("07/04/2014");
        Date dateEx3 = df.parse("23/07/2018");


        Book b1 = new Book("Night", 5, datePub1, dateRec1);
        Book b2 = new Book("War", 6, datePub2, dateRec2);
        Book b3 = new Book("Rome", 2, datePub3, dateRec3);
        Book b4 = new Book("Space", 9, datePub4, dateRec4);
        Book b5 = new Book("Knight", 7, datePub5, dateRec5);

        Book[] books1 = {b1, b3, b5};
        Book[] books2 = {b2, b4};
        Book[] books3 = {b1, b3, b4, b5};

        Order o1 = new Order(r1, dateEx1, books1);
        Order o2 = new Order(r2, dateEx2, books2);
        Order o3 = new Order(r2, dateEx3, books3);


        Request req1 = new Request(b1, r3);
        Request req2 = new Request(b2, r4);
        Request req3 = new Request(b2, r4);
        Request req4 = new Request(b2, r3);
        Request req5 = new Request(b1, r3);

        BookRepository bookRepository = new BookRepository();
        BookManager bookManager = new BookManager(bookRepository);
        OrderRepository orderRepository = new OrderRepository();
        OrderManager orderManager = new OrderManager(orderRepository);
        RequestRepository requestRepository = new RequestRepository();
        RequestManager requestManager = new RequestManager(requestRepository, bookRepository);
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

        fileWorker.saveToFile(bookRepository.getBooks());
        fileWorker.saveToFile(orderRepository.getOrders());
        fileWorker.saveToFile(requestRepository.getReaders());

        //store.showBookInfo(b1.getId());
        //store.showAllPrice();
        //store.showBooksSortedBy(SortingType.DATE);
        //store.showUnsoldBooksSortedBy(SortingType.DATE);
        //store.showOrdersSortedBy(SortingType.DATE);
        //store.showExecutedOrdersSortedBy(SortingType.PRICE);
        //store.showRequestsSortedBy(SortingType.ALPHABET);
    }
}
