package com.senla.controller.manager;

import com.senla.api.dao.IOrderDao;
import com.senla.api.manager.IOrderManager;

import com.senla.controller.dao.DaoFactory;
import com.senla.controller.dao.mysql.OrderDao;
import com.senla.dependencyinjection.DependencyInjection;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.OrderHandler;
import com.senla.model.entity.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderManager implements IOrderManager{
    private IOrderDao orderDao;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private final static Logger LOGGER = Logger.getLogger(OrderManager.class);


    public OrderManager() {
        orderDao = (IOrderDao) DependencyInjection.getInstance().getObject(IOrderDao.class);
    }

    @Override
    public void add(Order order) {
        orderDao.create(order);
    }

    @Override
    public void clone(int id) {
        orderDao.clone(id);
    }

    @Override
    public void cancel(int id) {
        orderDao.update(id);
    }

    @Override
    public int getAllPrice(){
        String query = "SELECT SUM(price) AS allPrice FROM book_order";

        try (
            Connection connection = daoFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query)
        ){
            if (result.next()) {
                return result.getInt("allPrice");
            }
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
        return 0;
    }

    @Override
    public int getAmountExecutedOrders(){
        String query = "SELECT COUNT() AS count FROM book_order WHERE status = CANCELED";

        try (
                Connection connection = daoFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(query)
        ){
            if (result.next()) {
                return result.getInt("count");
            }
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
        return 0;
    }

    @Override
    public Order findById(int id){
        return orderDao.findById(id);
    }

    @Override
    public List<Order> sortOrders(String query, String column){
        String sql = query + column + ";";

        LOGGER.trace("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
            ResultHandler<List<Order>> orders = new OrderHandler();
            return Executor.execQuery(connection, sql, orders);
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
        return null;
    }
}
