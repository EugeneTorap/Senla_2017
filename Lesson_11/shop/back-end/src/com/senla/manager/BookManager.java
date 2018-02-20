package com.senla.manager;

import com.senla.api.dao.IBookDao;
import com.senla.api.manager.IBookManager;
import com.senla.api.model.IBook;
import com.senla.api.model.IEntity;
import com.senla.connector.DBConnector;
import com.senla.dao.DaoException;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import com.senla.util.ArrayWorker;
import org.apache.log4j.Logger;

import java.util.List;

public class BookManager implements IBookManager {

    private IBookDao bookDao;
    private final static Logger LOGGER = Logger.getLogger(BookManager.class);

    public BookManager() throws Exception {
        DBConnector connector = DBConnector.getInstance();
        bookDao = (IBookDao) DependencyInjection.getInstance().getObject(connector, IBookDao.class);
    }

    @Override
    public void create(IBook book) {
        try {
            bookDao.create(book);
        } catch (Exception e) {
            LOGGER.error("Method create(IBook book) is failed", e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            bookDao.delete(id);
        } catch (Exception e) {
            LOGGER.error("Method delete(int id) is failed", e);
        }
    }

    @Override
    public IBook getById(int id) {
        try {
            return bookDao.getById(id);
        } catch (Exception e) {
            LOGGER.error("Method getById(int id) is failed", e);
        }
        return null;
    }

    @Override
    public List<IBook> getAll(String sort) {
        try {
            return bookDao.getAll(sort);
        } catch (Exception e) {
            LOGGER.error("Method getAll(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public List<IBook> getAllUnsold(String sort) {
        try {
            return bookDao.getAllUnsold(sort);
        } catch (Exception e) {
            LOGGER.error("Method getAllUnsold(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IBook.class);
        List<IBook> parsedReaders = (List<IBook>) Parser.parse(IBook.class, lines);

        try {
            List<IBook> books = getAll(null);
            for (IBook book : parsedReaders) {
                if (ArrayWorker.isExist(books, book.getId())) {
                    bookDao.update(book);
                } else {
                    bookDao.create(book);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Method importFromCsv() is failed", e);
        }
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
