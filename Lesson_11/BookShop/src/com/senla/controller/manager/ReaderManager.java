package com.senla.controller.manager;

import com.senla.api.dao.IReaderDao;
import com.senla.api.manager.IReaderManager;
import com.senla.controller.dao.DaoFactory;
import com.senla.dependencyinjection.DependencyInjection;
import com.senla.executor.Executor;
import com.senla.executor.ResultHandler;
import com.senla.executor.handler.ReaderHandler;
import com.senla.model.entity.Reader;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class ReaderManager implements IReaderManager{
    private IReaderDao readerDao;
    private final static Logger LOGGER = Logger.getLogger(ReaderManager.class);


    public ReaderManager() {
        readerDao = (IReaderDao) DependencyInjection.getInstance().getObject(IReaderDao.class);
    }

    @Override
    public void add(Reader reader) {
        readerDao.create(reader);
    }

    @Override
    public Reader findById(int id){
        return readerDao.findById(id);
    }

    @Override
    public List<Reader> sortReaders(String query, String column){
        String sql = query + column + ";";

        LOGGER.trace("Open connection");
        Connection connection = DaoFactory.getInstance().getConnection();
        ResultHandler<List<Reader>> readers = new ReaderHandler();
        return Executor.execQuery(connection, sql, readers);
    }
}
