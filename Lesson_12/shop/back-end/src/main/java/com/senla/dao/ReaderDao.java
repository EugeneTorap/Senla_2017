package com.senla.dao;

import com.senla.api.dao.IReaderDao;
import com.senla.api.model.IReader;

public class ReaderDao extends GenericDao<IReader> implements IReaderDao {

    protected ReaderDao(Class clazz) {
        super(clazz);
    }
}
