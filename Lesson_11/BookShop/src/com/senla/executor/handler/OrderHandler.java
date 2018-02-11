package com.senla.executor.handler;

import com.senla.enums.Status;
import com.senla.executor.ResultHandler;
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
            int orderReaderId = resultSet.getInt("readerId");
            String orderName = resultSet.getString("name");
            Date dateExecuted = resultSet.getDate("dateExecuted");
            int price = resultSet.getInt("price");
            Status status = Status.valueOf(resultSet.getString("status"));
            orders.add(new Order(orderId, new Reader(orderReaderId, orderName), dateExecuted, status, price));
        }
        return orders;
    }
}
