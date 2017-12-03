package com.senla.controller.manager;

import com.senla.api.manager.IReaderManager;
import com.senla.csv.Parser;
import com.senla.model.entity.Reader;
import com.senla.controller.repositories.ReaderRepository;
import com.senla.util.*;

import java.util.List;

public class ReaderManager implements IReaderManager{
    private ReaderRepository readerRepository;
    private Serializer serializer = new Serializer();


    public ReaderManager() {
        readerRepository = ReaderRepository.getInstance();
    }

    @Override
    public void add(Reader newReader) {
        readerRepository.add(newReader);
    }

    @Override
    public List<Reader> getReaders(){
        return readerRepository.getReaders();
    }

    @Override
    public void serialize(){
        serializer.save(readerRepository.getReaders(), MyProperty.getInstance().getProperty("readerpath"));
    }

    @Override
    public void deserialize() {
        readerRepository.setReaders((List<Reader>) serializer.load(MyProperty.getInstance().getProperty("readerpath")));
    }
}
