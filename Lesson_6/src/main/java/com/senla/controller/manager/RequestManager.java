package com.senla.controller.manager;

import com.senla.entity.Book;
import com.senla.controller.repositories.*;
import com.senla.entity.Request;
import com.senla.util.*;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {
    private RequestRepository requestRepository = new RequestRepository();
    private Serializer serializer = new Serializer();


    public RequestManager() {
        requestRepository = RequestRepository.getInstance();
    }

    public void saveToFile(){
        serializer.save(requestRepository.getRequests(), MyProperty.getInstance().getProperty("requestpath"));
    }

    public void loadFromFile(){
        requestRepository.setRequests((List<Request>) serializer.load(MyProperty.getInstance().getProperty("requestpath")));
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
        for (Book book : BookRepository.getInstance().getBooks()) {
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
        for (Book book: BookRepository.getInstance().getBooks()) {
            book.setRequestAmount(requestCount(book));
        }
    }

    public void exportRequest() {
        FileWorker.save(requestRepository.getRequests(), MyProperty.getInstance().getProperty("csvpath"));
    }

    public void importRequest() {
        int index;
        for (Request request : Parser.parseRequest(FileWorker.load(MyProperty.getInstance().getProperty("csvpath")),
                BookRepository.getInstance().getBooks(), ReaderRepository.getInstance().getReaders())) {
            if ((index = ArrayWorker.searchIndex(requestRepository.getRequests(), request.getId())) != -1){
                requestRepository.getRequests().set(index, request);
            } else {
                requestRepository.addRequest(request);
            }
        }
    }
}
