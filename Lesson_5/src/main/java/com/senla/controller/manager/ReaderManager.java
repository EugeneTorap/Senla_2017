package com.senla.controller.manager;

import com.senla.api.manager.IReaderManager;
import com.senla.entity.Reader;
import com.senla.controller.repositories.ReaderRepository;
import com.senla.util.*;

import java.util.List;

public class ReaderManager implements IReaderManager{
    private ReaderRepository readerRepository;


    public ReaderManager() {
        readerRepository = ReaderRepository.getInstance();
    }

    @Override
    public void add(Reader newReader) {
        readerRepository.add(newReader);
    }

    @Override
    public Reader search(int id) {
        return ArrayWorker.search(readerRepository.getReaders(), id);
    }

    @Override
    public void exportToFile() {
        FileWorker.save(readerRepository.getReaders(), "data/reader.txt");
    }

    @Override
    public void importFromFile() {
        int index;
        for (Reader reader : Parser.parseReader(FileWorker.load("data/reader.txt"))) {
            if ((index = ArrayWorker.searchIndex(readerRepository.getReaders(), reader.getId())) != -1){
                readerRepository.getReaders().set(index, reader);
            } else {
                readerRepository.add(reader);
            }
        }
    }
}
