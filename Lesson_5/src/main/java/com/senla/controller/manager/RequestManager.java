package com.senla.controller.manager;

import com.senla.api.manager.IRequestManager;
import com.senla.entity.Book;
import com.senla.controller.repositories.*;
import com.senla.entity.Request;
import com.senla.util.*;
import java.util.List;

public class RequestManager implements IRequestManager{
    private RequestRepository requestRepository;


    public RequestManager() {
        requestRepository = RequestRepository.getInstance();
    }

    @Override
    public void add(Request request) {
        requestRepository.add(request);
    }

    @Override
    public void showBookRequests(){
        System.out.println();
        for (Book book : BookRepository.getInstance().getBooks()) {
            Printer.print(book.toStringForRequest());
            for (Request request : requestRepository.getRequests()) {
                if (request.getBook().getId() == book.getId()) {
                    Printer.print(request.toStringForRequest());
                }
            }
            Printer.print("-----------------------");
        }
    }

    @Override
    public void setRequestAmount() {
        int count = 0;
        for (Book book: BookRepository.getInstance().getBooks()) {
            for (Request request : requestRepository.getRequests()) {
                if (request.getBook().getId() == book.getId()) {
                    count++;
                }
            }
            book.setRequestAmount(count);
            count = 0;
        }
    }

    @Override
    public void exportToFile() {
        FileWorker.save(requestRepository.getRequests(), "data/request.txt");
    }

    @Override
    public void importFromFile() {
        int index;
        for (Request request : Parser.parseRequest(FileWorker.load("data/request.txt"),
                BookRepository.getInstance().getBooks(), ReaderRepository.getInstance().getReaders())) {
            if ((index = ArrayWorker.searchIndex(requestRepository.getRequests(), request.getId())) != -1){
                requestRepository.getRequests().set(index, request);
            } else {
                requestRepository.add(request);
            }
        }
    }
}
