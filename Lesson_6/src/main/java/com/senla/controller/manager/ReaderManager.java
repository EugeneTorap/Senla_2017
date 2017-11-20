package com.senla.controller.manager;

import com.senla.entity.Reader;
import com.senla.controller.repositories.ReaderRepository;
import com.senla.util.ArrayWorker;
import com.senla.util.FileWorker;

import java.util.List;

public class ReaderManager {
    private ReaderRepository readerRepository = new ReaderRepository();
    private FileWorker fileWorker = new FileWorker();


    public void saveToFile(){
        fileWorker.save(readerRepository.getReaders(), "data/reader.bin");
    }

    public void loadFromFile() {
        readerRepository.setReaders((List<Reader>) fileWorker.load("data/reader.bin"));
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
}
