package com.senla.dao;

import com.senla.api.dao.IReaderDao;
import com.senla.api.model.IReader;
import com.senla.model.Reader;

public class ReaderDao extends GenericDao<IReader> implements IReaderDao {

    protected ReaderDao() {
        super(Reader.class);
    }
}
