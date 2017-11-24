package com.senla.controller.repositories;

import com.senla.entity.Reader;

import java.util.ArrayList;
import java.util.List;

public class ReaderRepository {
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


    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public void addReader(Reader reader){
        readers.add(reader);
    }
}
