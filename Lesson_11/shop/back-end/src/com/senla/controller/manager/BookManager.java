package com.senla.controller.manager;

import com.senla.api.dao.IBookDao;
import com.senla.api.manager.IBookManager;
import com.senla.api.model.IBook;
import com.senla.connector.DBConnector;
import com.senla.di.DependencyInjection;

import java.util.List;

public class BookManager implements IBookManager {
    private IBookDao bookDao;

    public BookManager() {
        DBConnector connector = DBConnector.getInstance();
        bookDao = (IBookDao) DependencyInjection.getInstance().getObject(connector, IBookDao.class);
    }

    @Override
    public void create(IBook book) {
        bookDao.create(book);
    }

    @Override
    public void delete(int id) {
        bookDao.delete(id);
    }

    @Override
    public IBook getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public List<IBook> getAll(String sort) {
        return bookDao.getAll(sort);
    }

    @Override
    public List<IBook> getAllUnsold(String sort) {
        return bookDao.getAllUnsold(sort);
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
