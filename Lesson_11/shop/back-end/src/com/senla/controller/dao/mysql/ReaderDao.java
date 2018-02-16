package com.senla.controller.dao.mysql;

import com.senla.api.dao.IReaderDao;
import com.senla.api.model.IReader;
import com.senla.connector.DBConnector;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.ReaderHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReaderDao extends ADao<IReader> implements IReaderDao {

    private static final String CREATE = "INSERT INTO reader(name, balance) VALUES (?,?);";
    private static final String UPDATE = "UPDATE reader SET status = CANCELED WHERE id = ? ;";
    private static final String DELETE = "DELETE FROM reader WHERE id = ";
    private static final String GET_BY_ID = "SELECT * FROM reader WHERE readerId = ";
    private static final String GET_ALL = "SELECT * FROM reader ORDER BY ";

    protected ReaderDao(DBConnector connector) {
        super(connector);
    }

    @Override
    protected String getCreate() {
        return CREATE;
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

    @Override
    protected String getDelete() {
        return DELETE;
    }

    @Override
    protected ResultHandler handle() {
        return new ReaderHandler();
    }

    @Override
    protected void fillCreateQuery(PreparedStatement statement, IReader reader) throws SQLException {
        statement.setString(1, reader.getName());
        statement.setInt(2, reader.getBalance());
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement statement, int id) throws SQLException {

    }
}
