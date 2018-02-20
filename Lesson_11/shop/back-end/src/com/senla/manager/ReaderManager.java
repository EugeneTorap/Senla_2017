package com.senla.manager;

import com.senla.api.dao.IReaderDao;
import com.senla.api.manager.IReaderManager;
import com.senla.api.model.IReader;
import com.senla.connector.DBConnector;
import com.senla.dao.DaoException;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import com.senla.util.ArrayWorker;
import org.apache.log4j.Logger;

import java.util.List;

public class ReaderManager implements IReaderManager {

    private IReaderDao readerDao;
    private final static Logger LOGGER = Logger.getLogger(ReaderManager.class);

    public ReaderManager() throws Exception {
        DBConnector connector = DBConnector.getInstance();
        readerDao = (IReaderDao) DependencyInjection.getInstance().getObject(connector, IReaderDao.class);
    }

    @Override
    public void create(IReader reader) {
        try {
            readerDao.create(reader);
        } catch (Exception e) {
            LOGGER.error("Method create(IReader reader) is failed", e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            readerDao.delete(id);
        } catch (Exception e) {
            LOGGER.error("Method delete(int id) is failed", e);
        }
    }

    @Override
    public IReader getById(int id) {
        try {
            return readerDao.getById(id);
        } catch (Exception e) {
            LOGGER.error("Method getById(int id) is failed", e);
        }
        return null;
    }

    @Override
    public List<IReader> getAll(String sort) {
        try {
            return readerDao.getAll(sort);
        } catch (Exception e) {
            LOGGER.error("Method getAll(String sort) is failed", e);
        }
        return null;
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IReader.class);
        List<IReader> parsedReaders = (List<IReader>) Parser.parse(IReader.class, lines);

        try {
            List<IReader> readers = getAll(null);
            for (IReader reader : parsedReaders) {
                if (ArrayWorker.isExist(readers, reader.getId())) {
                    readerDao.update(reader);
                } else {
                    readerDao.create(reader);
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
