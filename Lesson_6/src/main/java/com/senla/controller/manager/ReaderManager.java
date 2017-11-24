package com.senla.controller.manager;

import com.senla.entity.Reader;
import com.senla.controller.repositories.ReaderRepository;
import com.senla.util.*;

import java.util.List;

public class ReaderManager {
    private ReaderRepository readerRepository;
    private Serializer serializer = new Serializer();


    public ReaderManager() {
        readerRepository = ReaderRepository.getInstance();
    }

    public void saveToFile(){
        serializer.save(readerRepository.getReaders(), MyProperty.getInstance().getProperty("readerpath"));
    }

    public void loadFromFile() {
        readerRepository.setReaders((List<Reader>) serializer.load(MyProperty.getInstance().getProperty("readerpath")));
    }

    public void addReader(Reader newReader){
        readerRepository.addReader(newReader);
    }

    public Reader searchReader(int id){
        return ArrayWorker.search(readerRepository.getReaders(), id);
    }

    public ReaderRepository getReaderRepository() {
        return readerRepository;
    }

    public void exportReader() {
        FileWorker.save(readerRepository.getReaders(), MyProperty.getInstance().getProperty("csvpath"));
    }

    public void importReader() {
        int index;
        for (Reader reader : Parser.parseReader(FileWorker.load(MyProperty.getInstance().getProperty("csvpath")))) {
            if ((index = ArrayWorker.searchIndex(readerRepository.getReaders(), reader.getId())) != -1){
                readerRepository.getReaders().set(index, reader);
            } else {
                readerRepository.addReader(reader);
            }
        }
    }
}
