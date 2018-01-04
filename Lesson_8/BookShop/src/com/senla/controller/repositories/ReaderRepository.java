package com.senla.controller.repositories;

import com.senla.api.repository.IReaderRepository;
import com.senla.model.entity.Reader;

import java.util.ArrayList;
import java.util.List;

public class ReaderRepository implements IReaderRepository {
    private List<Reader> readers;
    private static ReaderRepository instance = null;


    private ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    public static ReaderRepository getInstance() {
        if (instance == null) {
            instance = new ReaderRepository();
        }
        return instance;
    }


    @Override
    public void add(Reader reader) {
        readers.add(reader);
    }

    @Override
    public List<Reader> getReaders() {
        return readers;
    }

    @Override
    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }
}
