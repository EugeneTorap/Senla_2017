package com.senla.manager;

import com.senla.api.dao.IReaderDao;
import com.senla.api.manager.IReaderManager;
import com.senla.api.model.IReader;
import com.senla.csv.CSVWorker;
import com.senla.csv.Parser;
import com.senla.di.DependencyInjection;
import com.senla.executor.Executor;

import java.util.List;

public class ReaderManager implements IReaderManager {

    private IReaderDao readerDao;

    public ReaderManager() {
        readerDao = (IReaderDao) DependencyInjection.getInstance().getObject(IReaderDao.class);
    }

    @Override
    public void create(IReader reader) {
        Executor.transact(session -> {
            readerDao.create(session, reader);
            return null;
        });
    }

    @Override
    public void delete(int id) {
        Executor.transact(session -> {
            IReader reader = readerDao.getById(session, id);
            if (reader != null){
                readerDao.delete(session, reader);
            }
            return null;
        });
    }

    @Override
    public IReader getById(int id) {
        return Executor.transact(session -> readerDao.getById(session, id));
    }

    @Override
    public List<IReader> getAll(String sort) {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> readerDao.getAll(session, sorting));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() {
        List<String> lines = CSVWorker.loadCsvStrings(IReader.class);

        Executor.transact(session -> {
            for (IReader reader: (List<IReader>) Parser.parse(IReader.class, lines)){
                readerDao.saveOrUpdate(session, reader);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() {
        CSVWorker.saveToCSV(getAll(null));
    }
}
