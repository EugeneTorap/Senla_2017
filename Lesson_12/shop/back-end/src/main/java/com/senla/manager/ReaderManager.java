package com.senla.manager;

import com.senla.api.dao.IReaderDao;
import com.senla.api.manager.IReaderManager;
import com.senla.api.model.IReader;
import com.senla.connector.DBConnector;
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
        readerDao.create(reader);
    }

    @Override
    public void delete(int id) {
        IReader reader = getById(id);
        if (reader != null){
            readerDao.delete(reader);
        }
    }

    @Override
    public IReader getById(int id) {
        return readerDao.getById(id);
    }

    @Override
    public List<IReader> getAll(String sort) {
        return readerDao.getAll(sort);
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IReader.class);

        for (IReader reader: (List<IReader>) Parser.parse(IReader.class, lines)){
            readerDao.saveOrUpdate(reader);
        }
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
