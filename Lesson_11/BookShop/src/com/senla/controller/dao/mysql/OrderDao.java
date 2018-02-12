package com.senla.controller.dao.mysql;

import com.senla.api.dao.IOrderDao;
import com.senla.controller.dao.DaoFactory;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.OrderHandler;
import com.senla.model.entity.Order;
import com.senla.util.DateConverter;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements IOrderDao {
    private final static Logger LOGGER = Logger.getLogger(OrderDao.class);

    @Override
    public void create(Order order) {
        String sql = "INSERT INTO book_order(readerId, dateExecuted, price) VALUES (?,?,?);";


        LOGGER.trace("Open connection");
        Connection connection = DaoFactory.getInstance().getConnection();
        try (
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            LOGGER.trace("Fill prepared statement");

            statement.setInt(1, order.getReader().getId());
            statement.setDate(2, DateConverter.valueOf(order.getDateExecuted()));
            statement.setInt(3, order.getPrice());

            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Can't close prepared statement", e);
        }
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM book_order INNER JOIN reader USING(readerId) WHERE orderId = " + id + ";";

        LOGGER.trace("Open connection");
        Connection connection = DaoFactory.getInstance().getConnection();
        ResultHandler<List<Order>> orders = new OrderHandler();
        Order order = Executor.execQuery(connection, sql, orders).get(0);
        if (order != null){
            return order;
        }
        LOGGER.warn("Order is null");
        return null;
    }

    @Override
    public void update(int id) {
        String sql = "UPDATE book_order SET status = CANCELED WHERE orderId = " + id + ";";

        LOGGER.trace("Open connection");
        Connection connection = DaoFactory.getInstance().getConnection();
        Executor.execUpdate(connection, sql);
    }

    @Override
    public void clone(int id) {
        Order order = findById(id);
        assert order != null;
        try {
            create(order.clone());
        }
        catch (CloneNotSupportedException e) {
            LOGGER.error("Failed clone", e);
        }
    }
}
