package com.senla.controller.manager;

import com.senla.api.manager.IReaderManager;
import com.senla.api.repository.IReaderRepository;
import com.senla.csv.Parser;
import com.senla.dependencyinjection.DependencyInjection;
import com.senla.model.entity.Reader;
import com.senla.controller.repositories.ReaderRepository;
import com.senla.util.*;

import java.util.List;

public class ReaderManager implements IReaderManager{
    private IReaderRepository readerRepository;
    private Serializer serializer = new Serializer();


    public ReaderManager() {
        readerRepository = (IReaderRepository) DependencyInjection.getInstance().getObject(ReaderRepository.class);
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
