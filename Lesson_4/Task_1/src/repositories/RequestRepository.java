package repositories;

import util.Checker;
import entity.Book;
import entity.Request;

public class RequestRepository {
    private Request[] requests = new Request[10];


    public Request[] getRequests() {
        return requests;
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

    public void addRequest(Request request){
        if (Checker.getPosition(requests) != -1) {
            int position = Checker.getPosition(requests);
            requests [position] = request;
            return;
        }
        System.out.println("Order repository is full");
    }
}
