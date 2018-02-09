package com.senla.controller.manager;

import com.senla.api.manager.IBookManager;
import com.senla.controller.dao.DaoFactory;
import com.senla.controller.dao.mysql.BookDao;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.BookHandler;
import com.senla.model.entity.Book;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookManager implements IBookManager {
    private BookDao bookDao;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private final static Logger LOGGER = Logger.getLogger(BookManager.class);


    public BookManager() {
        bookDao = new BookDao();
    }

    @Override
    public void add(Book book) {
        bookDao.create(book);
    }

    @Override
    public void addOnStore(int id) {
        bookDao.update(id, true);
    }

    @Override
    public void delFromStore(int id) {
        bookDao.update(id, false);
    }

    @Override
    public Book findById(int id){
        return bookDao.findById(id);
    }

    @Override
    public List<Book> sortBooks(String query, String column){
        String sql = query + column + ";";

        LOGGER.trace("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
            ResultHandler<List<Book>> books = new BookHandler();
            return Executor.execQuery(connection, sql, books);
        }
        catch (SQLException e){
            LOGGER.error("Can't close", e);
        }
        return null;
    }
}
