package com.senla.controller.repositories;

import com.senla.api.repository.IReaderRepository;
import com.senla.entity.Reader;

import java.util.ArrayList;
import java.util.List;

public class ReaderRepository implements IReaderRepository {
    private List<Reader> readers = new ArrayList<>();
    private static volatile ReaderRepository instance = null;


    public static ReaderRepository getInstance() {
        if (instance == null) {
            synchronized (ReaderRepository.class){
                if (instance == null) {
                    instance = new ReaderRepository();
                }
            }
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
