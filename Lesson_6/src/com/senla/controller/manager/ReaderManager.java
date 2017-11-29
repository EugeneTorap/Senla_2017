package com.senla.controller.manager;

import com.senla.api.manager.IReaderManager;
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
    public void exportToFile() {
        FileWorker.save(readerRepository.getReaders(), MyProperty.getInstance().getProperty("csvpath"));
    }

    @Override
    public void importFromFile() {
        int index;
        for (Reader reader : Parser.parseReader(FileWorker.load(MyProperty.getInstance().getProperty("csvpath")))) {
            if ((index = ArrayWorker.searchIndex(readerRepository.getReaders(), reader.getId())) != -1){
                readerRepository.getReaders().set(index, reader);
            } else {
                readerRepository.add(reader);
            }
        }
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
