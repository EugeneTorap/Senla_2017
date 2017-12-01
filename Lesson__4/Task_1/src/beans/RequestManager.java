package beans;

import entity.Book;
import repositories.BookRepository;
import util.Checker;
import entity.Request;
import repositories.RequestRepository;

public class RequestManager {
    private RequestRepository requestRepository;
    private BookRepository bookRepository;

    public RequestManager(RequestRepository requestRepository, BookRepository bookRepository) {
        this.requestRepository = requestRepository;
        this.bookRepository = bookRepository;
    }


    private int requestCount(Book book){
        int count = 0;
        for (Request request : requestRepository.getRequests()) {
            if (request != null && request.getBook().equals(book)) {
                count++;
            }
        }
        return count;
    }

    public Request[] requestForBook(Book book){
        if (requestCount(book) != 0){
            Request[] bookRequests = new Request[requestCount(book)];

            for (Request request : requestRepository.getRequests()) {
                if (Checker.getPosition(bookRequests) != -1 && request != null && request.getBook().equals(book)) {
                    int position = Checker.getPosition(bookRequests);
                    bookRequests[position] = request;
                }
            }
            return bookRequests;
        }
        return null;
    }

    public void setRequestAmount() {
        for (Book book: bookRepository.getBooks()) {
            if (book != null) {
                book.setRequestAmount(requestCount(book));
            }
        }
    }

    public RequestRepository getRequestRepository() {
        return requestRepository;
    }
}
