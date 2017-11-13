package manager;

import entity.Reader;
import repositories.ReaderRepository;
import util.FileWorker;

public class ReaderManager {
    private ReaderRepository readerRepository = new ReaderRepository();
    private FileWorker fileWorker = new FileWorker("book.txt", "reader.txt",
            "order.txt", "request.txt");


    public void saveToFile(){
        fileWorker.saveReaders(readerRepository.getReaders());
    }

    public void loadFromFile(){
        readerRepository.setReaders(fileWorker.loadReader());
    }

    public void addReader(Reader newReader){
        readerRepository.addReader(newReader);
    }

    public ReaderRepository getReaderRepository() {
        return readerRepository;
    }
}
