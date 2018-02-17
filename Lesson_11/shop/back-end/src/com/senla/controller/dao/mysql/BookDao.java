package com.senla.controller.dao.mysql;

import com.senla.api.dao.IBookDao;
import com.senla.api.model.IBook;
import com.senla.connector.DBConnector;
import com.senla.controller.dao.DaoException;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.BookHandler;
import com.senla.util.DateConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookDao extends ADao<IBook> implements IBookDao {

    private DBConnector connector;

    private static final String CREATE = "INSERT INTO book(title, isStore, requestAmount," +
            " dateReceipted, datePublished, price) VALUES (?,?,?,?,?);";
    private static final String UPDATE = "UPDATE book SET title = ? , isStore = ? , dateReceipted = ? , datePublished = ? , price = ? WHERE id = ? ;";
    private static final String DELETE = "DELETE FROM book WHERE id = ";
    private static final String GET_BY_ID = "SELECT * FROM book WHERE id = ";
    private static final String GET_ALL = "SELECT * FROM book ORDER BY ";
    private static final String GET_ALL_UNSOLD = "SELECT * FROM book WHERE dateReceipted < (SELECT DATE_SUB((SELECT CURDATE()), INTERVAL 6 MONTH)) ORDER BY ";

    protected BookDao(DBConnector connector) {
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

    protected ResultHandler<List<IBook>> handle() {
        return new BookHandler();
    }

    protected void fillCreateQuery(PreparedStatement statement, IBook book) throws SQLException {
        statement.setString(1, book.getTitle());
        statement.setBoolean(2, book.getTheBookInStore());
        statement.setDate(3, DateConverter.valueOf(book.getDateReceipted()));
        statement.setDate(4, DateConverter.valueOf(book.getDatePublished()));
        statement.setInt(5, book.getPrice());
    }

    protected void fillUpdateQuery(PreparedStatement statement, int id) throws SQLException {
        statement.setInt(6, id);
    }

    @Override
    public List<IBook> getAllUnsold(String sort) throws DaoException {
        Connection connection = connector.getConnection();
        if (sort == null){
            sort = "id";
        }
        String sql = GET_ALL_UNSOLD + sort;
        return Executor.execQuery(connection, sql, handle());
    }
}
