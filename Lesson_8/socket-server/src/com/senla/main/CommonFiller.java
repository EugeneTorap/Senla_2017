package com.senla.main;

import com.senla.model.entity.Order;
import com.senla.model.entity.Book;
import com.senla.model.entity.Reader;
import com.senla.model.entity.Request;
import com.senla.view.facade.OnlineBookStore;
import com.senla.util.Printer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommonFiller {
    public static OnlineBookStore fillData() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        OnlineBookStore store = OnlineBookStore.getInstance();

        try {
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
            Date dateRec3 = df.parse("10/11/2017");
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

            List<Book> booksList1 = new ArrayList<>(Arrays.asList(books1));
            List<Book> booksList2 = new ArrayList<>(Arrays.asList(books2));
            List<Book> booksList3 = new ArrayList<>(Arrays.asList(books3));

            Order o1 = new Order(r1, dateEx1, booksList1);
            Order o2 = new Order(r2, dateEx2, booksList2);
            Order o3 = new Order(r2, dateEx3, booksList3);

            Request req1 = new Request(b1, r3);
            Request req2 = new Request(b2, r4);
            Request req3 = new Request(b2, r1);
            Request req4 = new Request(b1, r2);


            store.addReader(r1);
            store.addReader(r2);
            store.addReader(r3);
            store.addReader(r4);

            store.addBook(b1);
            store.addBook(b2);
            store.addBook(b3);
            store.addBook(b4);
            store.addBook(b5);

            store.addBookOnStore(b1.getId());
            store.addBookOnStore(b2.getId());

            store.addOrder(o1);
            store.addOrder(o2);
            store.addOrder(o3);

            store.addRequest(req1);
            store.addRequest(req2);
            store.addRequest(req3);
            store.addRequest(req4);
        } catch (ParseException e){
            Printer.print("ParseException");
        }
        return store;
    }
}
