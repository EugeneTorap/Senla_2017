package repositories;

import util.ArrayWorker;
import util.Checker;
import entity.Book;
import entity.Request;

public class RequestRepository {
    private Request[] requests = new Request[50];


    public Request[] getRequests() {
        return requests;
    }

    public void addRequest(Request request){
        if (Checker.getPosition(requests) == -1) {
            requests = ArrayWorker.extendArray(requests);
        }
        int position = Checker.getPosition(requests);
        requests [position] = request;
    }

    public Request[] requestForBook(Book book){
        Request[] bookRequests = new Request[10];

        for (Request request : requests) {
            if (Checker.getPosition(bookRequests) != -1 && request != null && request.getBook().equals(book)) {
                int position = Checker.getPosition(bookRequests);
                bookRequests[position] = request;
            }
        }
        return bookRequests;
    }
}
