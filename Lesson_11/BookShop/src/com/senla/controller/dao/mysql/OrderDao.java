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
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private final static Logger LOGGER = Logger.getLogger(OrderDao.class);


    @Override
    public void create(Order order) {
        String sql = "INSERT INTO book_order(readerId, dateExecuted, price) VALUES (?,?,?);";


        LOGGER.trace("Open connection");
        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            LOGGER.trace("Fill prepared statement");

            statement.setInt(1, order.getReader().getId());
            statement.setDate(2, DateConverter.valueOf(order.getDateExecuted()));
            statement.setInt(3, order.getPrice());

            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Can't close", e);
        }
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM book WHERE bookId = " + id + ";";

        LOGGER.trace("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
            ResultHandler<List<Order>> orders = new OrderHandler();
            Order order = Executor.execQuery(connection, sql, orders).get(0);
            if (order != null){
                return order;
            }
            LOGGER.warn("Order is null");
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
        return null;
    }

    @Override
    public void update(int id) {
        String sql = "UPDATE book SET status = CANCELED WHERE bookId = " + id + ";";

        LOGGER.trace("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
            Executor.execUpdate(connection, sql);
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
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
