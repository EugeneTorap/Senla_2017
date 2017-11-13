package repositories;

import entity.Reader;
import util.ArrayWorker;

import java.util.ArrayList;
import java.util.List;

public class ReaderRepository {
    private List<Reader> readers = new ArrayList<>();


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
