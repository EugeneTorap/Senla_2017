package com.senla.controller.manager;

import com.senla.api.dao.IReaderDao;
import com.senla.api.manager.IReaderManager;
import com.senla.api.model.IReader;
import com.senla.connector.DBConnector;
import com.senla.di.DependencyInjection;

import java.util.List;

public class ReaderManager implements IReaderManager{
    private IReaderDao readerDao;


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
        readerDao.delete(id);
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
    public String importToCsv() {
        return null;
    }

    @Override
    public String exportFromCsv() {
        return null;
    }
}
