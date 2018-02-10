package com.senla.controller.dao.mysql;

import com.senla.api.dao.IReaderDao;
import com.senla.controller.dao.DaoFactory;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.ReaderHandler;
import com.senla.model.entity.Reader;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReaderDao implements IReaderDao {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private final static Logger LOGGER = Logger.getLogger(ReaderDao.class);


    @Override
    public void create(Reader reader) {
        String sql = "INSERT INTO reader(name) VALUES (?);";


        LOGGER.trace("Open connection");
        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            LOGGER.trace("Fill prepared statement");

            statement.setString(1, reader.getName());

            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Can't close", e);
        }
    }

    @Override
    public Reader findById(int id) {
        String sql = "SELECT * FROM reader WHERE bookId = " + id + ";";

        LOGGER.trace("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
            ResultHandler<List<Reader>> readers = new ReaderHandler();
            Reader reader = Executor.execQuery(connection, sql, readers).get(0);
            if (reader != null){
                return reader;
            }
            LOGGER.warn("Order is null");
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
        return null;
    }
}
