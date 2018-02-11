package com.senla.executor.handler;

import com.senla.executor.ResultHandler;
import com.senla.model.entity.Book;
import com.senla.model.entity.Order;
import com.senla.model.entity.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHandler implements ResultHandler{
    @Override
    public List<Order> handle(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Reader> readers = new ArrayList<>();

        int orderId = 0;
        int orderReaderId = 0;
        String orderName = null;
        Date dateExecuted = null;
        int bookId = 0;
        String title = null;
        int price = 0;
        boolean isTheBookInStore = true;
        Date datePublished = null;
        Date dateReceipted = null;

        while (resultSet.next()) {

            if (orderId != resultSet.getInt("orderId")){

                if (orderId != 0){
                    orders.add(new Order(orderId, new Reader(orderReaderId, orderName), dateExecuted, books));
                }
                orderId = resultSet.getInt("orderId");
                orderReaderId = resultSet.getInt("readerId");
                orderName = resultSet.getString("name");
                dateExecuted = resultSet.getDate("dateExecuted");
                books = new ArrayList<>();
            }

            if (bookId != resultSet.getInt("bookId")){

                if (bookId != 0){
                    books.add(new Book(bookId, title, price, isTheBookInStore, datePublished, dateReceipted, readers));
                }
                bookId = resultSet.getInt("orderId");
                title = resultSet.getString("title");
                price = resultSet.getInt("price");
                isTheBookInStore = resultSet.getBoolean("isTheBookInStore");
                datePublished = resultSet.getDate("datePublished");
                dateReceipted = resultSet.getDate("dateReceipted");
                readers = new ArrayList<>();
            }

            int readerId = resultSet.getInt("readerId");
            String name = resultSet.getString("name");
            readers.add(new Reader(readerId, name));
        }
        return orders;
    }
}
