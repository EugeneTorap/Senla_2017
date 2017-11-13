package com.senla.manager;

import com.senla.entity.Reader;
import com.senla.repositories.ReaderRepository;
import com.senla.util.FileWorker;

public class ReaderManager {
    private ReaderRepository readerRepository = new ReaderRepository();
    private FileWorker fileWorker = new FileWorker();


    public void saveToFile(){
        fileWorker.save(readerRepository.getReaders(), "reader.txt");
    }

    public void loadFromFile(){
        readerRepository.setReaders(fileWorker.loadReader("reader.txt"));
    }

    public void addReader(Reader newReader){
        readerRepository.addReader(newReader);
    }

    public ReaderRepository getReaderRepository() {
        return readerRepository;
    }
}
