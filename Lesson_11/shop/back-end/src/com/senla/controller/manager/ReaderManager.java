package com.senla.controller.manager;

import com.senla.api.dao.IReaderDao;
import com.senla.api.manager.IReaderManager;
import com.senla.api.model.IEntity;
import com.senla.api.model.IReader;
import com.senla.connector.DBConnector;
import com.senla.controller.dao.DaoException;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import org.apache.log4j.Logger;

import java.util.List;

public class ReaderManager implements IReaderManager {

    private IReaderDao readerDao;
    private final static Logger LOGGER = Logger.getLogger(ReaderManager.class);

    public ReaderManager() {
        DBConnector connector = DBConnector.getInstance();
        readerDao = (IReaderDao) DependencyInjection.getInstance().getObject(connector, IReaderDao.class);
    }

    @Override
    public void create(IReader reader) {
        try {
            readerDao.create(reader);
        } catch (DaoException e) {
            LOGGER.error("Method create(IReader reader) is failed", e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            readerDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error("Method delete(int id) is failed", e);
        }
    }

    @Override
    public IReader getById(int id) {
        try {
            return readerDao.getById(id);
        } catch (DaoException e) {
            LOGGER.error("Method getById(int id) is failed", e);
        }
        return null;
    }

    @Override
    public List<IReader> getAll(String sort) {
        try {
            return readerDao.getAll(sort);
        } catch (DaoException e) {
            LOGGER.error("Method getAll(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IReader.class);
        List<IReader> parsedReaders = (List<IReader>) Parser.parse(IReader.class, lines);
        CSVWorker.setEntity((List<IEntity>)(List<?>)parsedReaders, (List<IEntity>)(List<?>)getAll(null));
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
