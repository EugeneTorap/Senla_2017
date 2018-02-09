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

        while (resultSet.next()) {
            int orderId = resultSet.getInt("orderId");
            int readerId = resultSet.getInt("readerId");
            String name = resultSet.getString("name");
            Date datePublished = resultSet.getDate("dateExecuted");
            List<Book> books = new ArrayList<>();
            orders.add(new Order(orderId, new Reader(readerId, name), datePublished, books));
        }
        return orders;
    }
}
