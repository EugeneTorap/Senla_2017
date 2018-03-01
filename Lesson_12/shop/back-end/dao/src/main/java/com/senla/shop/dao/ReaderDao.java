package com.senla.shop.dao;

import com.senla.shop.api.dao.IReaderDao;
import com.senla.shop.model.Reader;

public class ReaderDao extends GenericDao<Reader> implements IReaderDao {

    public ReaderDao() {
        super(Reader.class);
    }
}
