package com.senla.manager;

import com.senla.api.dao.IBookDao;
import com.senla.api.manager.IBookManager;
import com.senla.api.model.IBook;
import com.senla.connector.DBConnector;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import org.apache.log4j.Logger;

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
        bookDao.create(book);
    }

    @Override
    public void delete(int id) {
        IBook book = getById(id);
        if (book != null){
            bookDao.delete(book);
        }
    }

    @Override
    public IBook getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public List<IBook> getAll(String sort) {
        if (sort == null){
            sort = "id";
        }
        return bookDao.getAll(sort);
    }

    @Override
    public List<IBook> getAllUnsold(String sort) {
        if (sort == null){
            sort = "id";
        }
        return bookDao.getAllUnsold(sort);
    }

    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IBook.class);

        for (IBook book: (List<IBook>) Parser.parse(IBook.class, lines)){
            bookDao.saveOrUpdate(book);
        }
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
