package repositories;

import entity.Reader;
import util.ArrayWorker;
import util.Checker;
import entity.Request;

public class RequestRepository {
    private Request[] requests = new Request[50];
    private Reader[] readers = new Reader[50];


    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    public Reader[] getReaders() {
        return readers;
    }

    public void setReaders(Reader[] readers) {
        this.readers = readers;
    }

    public void addRequest(int id){
        if (Checker.getPosition(requests) == -1) {
            requests = ArrayWorker.extendArray(requests);
        }
        int position = Checker.getPosition(requests);
        requests [position] = ArrayWorker.search(requests, id);
    }

    public void addReader(Reader reader){
        if (Checker.getPosition(readers) == -1) {
            readers = ArrayWorker.extendArray(readers);
        }
        int position = Checker.getPosition(readers);
        readers [position] = reader;
    }
}
