package com.senla.controller.manager;

import com.senla.api.manager.IReaderManager;
import com.senla.controller.dao.mysql.ReaderDao;
import com.senla.model.entity.Order;
import com.senla.model.entity.Reader;

public class ReaderManager implements IReaderManager{
    private ReaderDao readerDao;


    public ReaderManager() {
        readerDao = new ReaderDao();
    }

    @Override
    public void add(Reader reader) {
        readerDao.create(reader);
    }

    @Override
    public Reader findById(int id){
        return readerDao.findById(id);
    }
}
