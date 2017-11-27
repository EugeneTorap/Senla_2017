package com.senla.api.repository;

import com.senla.entity.Reader;

import java.util.List;

public interface IReaderRepository {
    void add(Reader reader);
    List<Reader> getReaders();
    void setReaders(List<Reader> readers);
}
