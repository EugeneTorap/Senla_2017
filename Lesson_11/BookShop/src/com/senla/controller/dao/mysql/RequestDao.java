package com.senla.controller.dao.mysql;

import com.senla.controller.dao.DaoFactory;
import com.senla.model.entity.Request;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequestDao {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private final static Logger LOGGER = Logger.getLogger(RequestDao.class);


    public void create(Request request) {
        String sql = "INSERT INTO request(bookId, readerId) VALUES (?,?);";


        LOGGER.trace("Open connection");
        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            LOGGER.trace("Fill prepared statement");

            statement.setInt(1, request.getBook().getId());
            statement.setInt(2, request.getReader().getId());

            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Can't close", e);
        }
    }
}
