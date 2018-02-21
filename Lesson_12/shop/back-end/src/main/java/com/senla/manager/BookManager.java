package com.senla.manager;

import com.senla.api.dao.IBookDao;
import com.senla.api.manager.IBookManager;
import com.senla.api.model.IBook;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import com.senla.executor.Executor;

import java.util.List;

public class BookManager implements IBookManager {

    private IBookDao bookDao;

    public BookManager() {
        bookDao = (IBookDao) DependencyInjection.getInstance().getObject(IBookDao.class);
    }

    @Override
    public void create(IBook book) {
        Executor.transact(session -> {
            bookDao.create(session, book);
            return null;
        });
    }

    @Override
    public void delete(int id) {
        Executor.transact(session -> {
            IBook book = bookDao.getById(session, id);
            if (book != null){
                bookDao.delete(session, book);
            }
            return null;
        });
    }

    @Override
    public IBook getById(int id) {
        return Executor.transact(session -> bookDao.getById(session, id));

    }

    @Override
    public List<IBook> getAll(String sort) {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> bookDao.getAll(session, sorting));
    }

    @Override
    public List<IBook> getAllUnsold(String sort) {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> bookDao.getAllUnsold(session, sorting));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IBook.class);

        Executor.transact(session -> {
            for (IBook book: (List<IBook>) Parser.parse(IBook.class, lines)){
                bookDao.saveOrUpdate(session, book);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
