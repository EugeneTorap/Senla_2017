package com.senla.controller.dao.mysql;

import com.senla.api.dao.IBookDao;
import com.senla.controller.dao.DaoFactory;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.model.entity.Book;
import com.senla.executor.handler.BookHandler;
import com.senla.util.DateConverter;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookDao implements IBookDao {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private final static Logger LOGGER = Logger.getLogger(BookDao.class);


    @Override
    public void create(Book book) {
        String sql = "INSERT INTO book(title, isTheBookInStore, requestAmount," +
                " dateReceipted, datePublished, price) VALUES (?,?,?,?,?,?);";


        LOGGER.trace("Open connection");
        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            LOGGER.trace("Fill prepared statement");

            statement.setString(1, book.getTitle());
            statement.setBoolean(2, book.getTheBookInStore());
            statement.setInt(3, book.getRequestAmount());
            statement.setDate(4, DateConverter.valueOf(book.getDateReceipted()));
            statement.setDate(5, DateConverter.valueOf(book.getDatePublished()));
            statement.setInt(6, book.getPrice());

            statement.execute();
        }
        catch (SQLException e) {
            LOGGER.error("Can't close", e);
        }
    }

    @Override
    public Book findById(int id) {
        String sql = "SELECT * FROM book WHERE bookId = " + id + ";";

        LOGGER.trace("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
            ResultHandler<List<Book>> books = new BookHandler();
            Book book = Executor.execQuery(connection, sql, books).get(0);
            if (book != null){
                return book;
            }
            LOGGER.warn("Book is null");
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
        return null;
    }

    @Override
    public void update(int id, boolean status) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE book SET isTheBookInStore = ").append(status);
        sql.append(" WHERE bookId = ").append(id).append(";");

        LOGGER.trace("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
            Executor.execUpdate(connection, sql.toString());
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
    }
}
