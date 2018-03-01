package com.senla.shop.manager;

import com.senla.shop.api.dao.IBookDao;
import com.senla.shop.api.manager.IBookManager;
import com.senla.shop.csv.CSVWorker;
import com.senla.shop.csv.Parser;
import com.senla.shop.di.DependencyInjection;
import com.senla.shop.executor.Executor;
import com.senla.shop.model.Book;

import java.util.List;

public class BookManager implements IBookManager {

    private IBookDao bookDao;

    public BookManager() {
        bookDao = (IBookDao) DependencyInjection.getInstance().getObject(IBookDao.class);
    }

    @Override
    public void create(Book book) throws Exception {
        Executor.transact(session -> {
            bookDao.create(session, book);
            return null;
        });
    }

    @Override
    public void delete(int id) throws Exception {
        Executor.transact(session -> {
            Book book = bookDao.getById(session, id);
            if (book != null){
                bookDao.delete(session, book);
            }
            return null;
        });
    }

    @Override
    public Book getById(int id) throws Exception {
        return Executor.transact(session -> bookDao.getById(session, id));

    }

    @Override
    public List<Book> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> bookDao.getAll(session, sorting));
    }

    @Override
    public List<Book> getAllUnsold(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> bookDao.getAllUnsold(session, sorting));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() throws Exception {
        List<String> lines = CSVWorker.loadCsvStrings(Book.class);

        Executor.transact(session -> {
            for (Book book: (List<Book>) Parser.parse(Book.class, lines)){
                bookDao.saveOrUpdate(session, book);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() throws Exception {
        CSVWorker.saveToCSV(getAll(null));
    }
}
