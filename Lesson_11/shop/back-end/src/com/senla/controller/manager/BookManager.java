package com.senla.controller.manager;

import com.senla.api.dao.IBookDao;
import com.senla.api.manager.IBookManager;
import com.senla.api.model.IBook;
import com.senla.api.model.IEntity;
import com.senla.connector.DBConnector;
import com.senla.controller.dao.DaoException;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class BookManager implements IBookManager {

    private IBookDao bookDao;
    private final static Logger LOGGER = Logger.getLogger(BookManager.class);

    public BookManager() {
        DBConnector connector = DBConnector.getInstance();
        bookDao = (IBookDao) DependencyInjection.getInstance().getObject(connector, IBookDao.class);
    }

    @Override
    public void create(IBook book) {
        try {
            bookDao.create(book);
        } catch (DaoException e) {
            LOGGER.error("Method create(IBook book) is failed", e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            bookDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error("Method delete(int id) is failed", e);
        }
    }

    @Override
    public IBook getById(int id) {
        try {
            return bookDao.getById(id);
        } catch (DaoException e) {
            LOGGER.error("Method getById(int id) is failed", e);
        }
        return null;
    }

    @Override
    public List<IBook> getAll(String sort) {
        try {
            return bookDao.getAll(sort);
        } catch (DaoException e) {
            LOGGER.error("Method getAll(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public List<IBook> getAllUnsold(String sort) {
        try {
            return bookDao.getAllUnsold(sort);
        } catch (DaoException e) {
            LOGGER.error("Method getAllUnsold(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IBook.class);
        List<IBook> parsedReaders = (List<IBook>) Parser.parse(IBook.class, lines);
        CSVWorker.setEntity((List<IEntity>)(List<?>)parsedReaders, (List<IEntity>)(List<?>)getAll(null));
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
