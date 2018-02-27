package com.senla.shop.manager;

import com.senla.shop.api.dao.IReaderDao;
import com.senla.shop.api.manager.IReaderManager;
import com.senla.shop.api.model.IReader;
import com.senla.shop.csv.CSVWorker;
import com.senla.shop.csv.Parser;
import com.senla.shop.di.DependencyInjection;
import com.senla.shop.executor.Executor;

import java.util.List;

public class ReaderManager implements IReaderManager {

    private IReaderDao readerDao;

    public ReaderManager() {
        readerDao = (IReaderDao) DependencyInjection.getInstance().getObject(IReaderDao.class);
    }

    @Override
    public void create(IReader reader) throws Exception {
        Executor.transact(session -> {
            readerDao.create(session, reader);
            return null;
        });
    }

    @Override
    public void delete(int id) throws Exception {
        Executor.transact(session -> {
            IReader reader = readerDao.getById(session, id);
            if (reader != null){
                readerDao.delete(session, reader);
            }
            return null;
        });
    }

    @Override
    public IReader getById(int id) throws Exception {
        return Executor.transact(session -> readerDao.getById(session, id));
    }

    @Override
    public List<IReader> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> readerDao.getAll(session, sorting));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() throws Exception {
        List<String> lines = CSVWorker.loadCsvStrings(IReader.class);

        Executor.transact(session -> {
            for (IReader reader: (List<IReader>) Parser.parse(IReader.class, lines)){
                readerDao.saveOrUpdate(session, reader);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() throws Exception {
        CSVWorker.saveToCSV(getAll(null));
    }
}
