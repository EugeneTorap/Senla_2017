package com.senla.dao;

import com.senla.api.dao.IGenericDao;
import com.senla.connector.DBConnector;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDao<T> implements IGenericDao<T> {

    private DBConnector connector;
    private final static Logger LOGGER = Logger.getLogger(GenericDao.class);

    protected abstract String getCreate();
    protected abstract String getGetAll();
    protected abstract String getGetById();
    protected abstract String getUpdate();
    protected abstract String getDelete();

    protected abstract ResultHandler<List<T>> handle();
    protected abstract void fillCreateQuery(PreparedStatement statement, T t) throws SQLException;
    protected abstract void fillUpdateQuery(PreparedStatement statement, int id) throws SQLException;

    protected GenericDao(DBConnector connector){
        this.connector = connector;
    }

    @Override
    public void create(T t) throws Exception {
        Connection connection = connector.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(getCreate())) {
            fillCreateQuery(statement, t);
            statement.execute();
        }
        catch (SQLException e){
            LOGGER.error("Can't close prepared statement", e);
            throw new DaoException("Can't close prepared statement in method create(T t)", e);
        }
    }

    @Override
    public void update(int id) throws Exception {
        Connection connection = connector.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(getUpdate())) {
            fillUpdateQuery(statement, id);
            statement.execute();
        }
        catch (SQLException e){
            LOGGER.error("Can't close prepared statement", e);
            throw new DaoException("Can't close prepared statement in method update(int id)", e);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        Connection connection = connector.getConnection();
        String sql = getDelete() + id;
        Executor.execUpdate(connection, sql);
    }

    @Override
    public T getById(int id) throws Exception {
        Connection connection = connector.getConnection();
        String sql = getGetById() + id;
        return Executor.execQuery(connection, sql, handle()).get(0);
    }

    @Override
    public List<T> getAll(String sort) throws Exception {
        Connection connection = connector.getConnection();
        if (sort == null){
            sort = "id";
        }
        String sql = getGetAll() + sort;
        return Executor.execQuery(connection, sql, handle());
    }
}
