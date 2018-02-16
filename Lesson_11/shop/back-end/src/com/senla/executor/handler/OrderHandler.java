package com.senla.executor.handler;

import com.senla.api.creator.ICreator;
import com.senla.api.model.IOrder;
import com.senla.di.DependencyInjection;
import com.senla.enums.Status;
import com.senla.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHandler implements ResultHandler {

    private static ICreator creator = (ICreator) DependencyInjection.getInstance().getObject(ICreator.class);

    @Override
    public List<IOrder> handle(ResultSet resultSet) throws SQLException {
        List<IOrder> orders = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            Date dateExecuted = resultSet.getDate("dateExecuted");
            int price = resultSet.getInt("price");
            Status status = Status.valueOf(resultSet.getString("status"));
            orders.add(creator.createOrder(id, dateExecuted, status, price));
        }
        return orders;
    }
}
