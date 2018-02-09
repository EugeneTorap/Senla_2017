package com.senla.api.manager;

import com.senla.model.entity.Reader;

import java.util.List;

public interface IReaderManager {
    void add(Reader reader);
    Reader findById(int id);
}
