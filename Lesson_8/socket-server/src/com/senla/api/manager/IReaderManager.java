package com.senla.api.manager;

import com.senla.model.entity.Reader;

import java.util.List;

public interface IReaderManager extends IManager {
    void add(Reader newReader);
    List<Reader> getReaders();
}
