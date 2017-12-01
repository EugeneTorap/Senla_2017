package com.senla.api.manager;

import com.senla.entity.Reader;

public interface IReaderManager extends IManager {
    void add(Reader newReader);
    Reader search(int id);
}
