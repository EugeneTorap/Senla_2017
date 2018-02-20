package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.api.model.IOrder;
import com.senla.connector.DBConnector;
import com.senla.dao.DaoException;
import com.senla.dao.GenericDao;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.OrderHandler;
import com.senla.util.DateConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao extends GenericDao<IOrder> implements IOrderDao {

    private DBConnector connector;

    private static final String CREATE = "INSERT INTO book_order(readerId, dateExecuted, price) VALUES (?,?,?);";
    private static final String UPDATE = "UPDATE book_order SET status = CANCELED WHERE id = ? ;";
    private static final String DELETE = "DELETE FROM book_order WHERE id = ";
    private static final String GET_BY_ID = "SELECT * FROM book_order WHERE id = ";
    private static final String GET_ALL = "SELECT * FROM book_order ORDER BY ";
    private static final String GET_ALL_EXEC = "SELECT * FROM book_order WHERE status = 'EXECUTED' ORDER BY ";
    private static final String SUM = "SELECT SUM(price) AS allPrice FROM book_order;";
    private static final String COUNT = "SELECT COUNT(*) AS count FROM book_order WHERE status = 'CANCELED';";

    protected OrderDao(DBConnector connector) {
        super(connector);
        this.connector = connector;
    }

    @Override
    protected String getCreate() {
        return CREATE;
    }

    @Override
    protected String getDelete() {
        return DELETE;
    }

    @Override
    protected String getGetAll() {
        return GET_ALL;
    }

    @Override
    protected String getGetById() {
        return GET_BY_ID;
    }

    @Override
    protected String getUpdate() {
        return UPDATE;
    }

    protected ResultHandler<List<IOrder>> handle() {
        return new OrderHandler();
    }

    protected void fillCreateQuery(PreparedStatement statement, IOrder order) throws SQLException {
        statement.setInt(1, order.getReader().getId());
        statement.setDate(2, DateConverter.valueOf(order.getDateExecuted()));
        statement.setInt(3, order.getPrice());
    }

    protected void fillUpdateQuery(PreparedStatement statement, int id) throws SQLException {
        statement.setInt(4, id);
    }

    @Override
    public Integer getAllPrice() throws Exception {
        Connection connection = connector.getConnection();
        return Executor.execQuery(connection, SUM, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getInt("allPrice");
            }
            return null;
        });
    }

    @Override
    public Integer getAmountExecutedOrders() throws Exception {
        Connection connection = connector.getConnection();
        return Executor.execQuery(connection, COUNT, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
            return null;
        });
    }

    @Override
    public List<IOrder> getAllExec(String sort) throws Exception {
        Connection connection = connector.getConnection();
        if (sort == null){
            sort = "id";
        }
        String sql = GET_ALL_EXEC + sort;
        return Executor.execQuery(connection, sql, handle());
    }
}
