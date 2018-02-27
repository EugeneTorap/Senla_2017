package com.senla.shop.dao;

import com.senla.shop.api.dao.IReaderDao;
import com.senla.shop.api.model.IReader;
import com.senla.shop.model.Reader;

public class ReaderDao extends GenericDao<IReader> implements IReaderDao {

    protected ReaderDao() {
        super(Reader.class);
    }
}
