package com.senla.api.dao;

import com.senla.model.entity.Reader;

public interface IReaderDao {
    void create(Reader reader);
    Reader findById(int id);
}
