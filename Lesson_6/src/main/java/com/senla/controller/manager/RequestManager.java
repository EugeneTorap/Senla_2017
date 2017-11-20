package com.senla.controller.manager;

import com.senla.entity.Book;
import com.senla.controller.repositories.*;
import com.senla.entity.Request;
import com.senla.util.FileWorker;
import com.senla.util.MyProperty;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {
    private RequestRepository requestRepository = new RequestRepository();
    private BookRepository bookRepository;
    private FileWorker fileWorker = new FileWorker();


    public RequestManager(BookManager bookManager) {
        this.bookRepository = bookManager.getBookRepository();
    }

    public void saveToFile(){
        fileWorker.save(requestRepository.getRequests(), MyProperty.getMyProperty("requestpath"));
    }

    public void loadFromFile(){
        requestRepository.setRequests((List<Request>) fileWorker.load(MyProperty.getMyProperty("requestpath")));
    }

    public void addRequest(Request request){
        requestRepository.addRequest(request);
    }

    private int requestCount(Book book){
        int count = 0;
        for (Request request : requestRepository.getRequests()) {
            if (request.getBook().equals(book)) {
                count++;
            }
        }
        return count;
    }

    public void showBookRequests(){
        System.out.println();
        for (Book book : bookRepository.getBooks()) {
            System.out.println(book.getTitle() + " " + book.getRequestAmount());
            if (requestForBook(book) != null){
                for (Request request : requestForBook(book)) {
                    System.out.println(request.getId() + " " + request.getReader().getName());
                }
            }
            System.out.println("-----------------------");
        }
    }

    private List<Request> requestForBook(Book book){
        if (requestCount(book) != 0){
            List<Request> bookRequests = new ArrayList<>();

            for (Request request : requestRepository.getRequests()) {
                if (request.getBook().equals(book)) {
                    bookRequests.add(request);
                }
            }
            return bookRequests;
        }
        return null;
    }

    public void setRequestAmount() {
        for (Book book: bookRepository.getBooks()) {
            book.setRequestAmount(requestCount(book));
        }
    }
}
