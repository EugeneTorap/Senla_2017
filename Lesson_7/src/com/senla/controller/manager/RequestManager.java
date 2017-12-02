package com.senla.controller.manager;

import com.senla.api.manager.IRequestManager;
import com.senla.model.entity.Book;
import com.senla.controller.repositories.*;
import com.senla.model.entity.Request;
import com.senla.util.*;
import java.util.List;

public class RequestManager implements IRequestManager{
    private RequestRepository requestRepository;
    private Serializer serializer = new Serializer();


    public RequestManager() {
        requestRepository = RequestRepository.getInstance();
    }

    @Override
    public void add(Request request) {
        requestRepository.add(request);
    }

    @Override
    public List<Request> getRequests(){
        return requestRepository.getRequests();
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
        FileWorker.save(requestRepository.getRequests(), MyProperty.getInstance().getProperty("csvpath"));
    }

    @Override
    public void importFromFile() {
        int index;
        for (Request request : Parser.parseRequest(FileWorker.load(MyProperty.getInstance().getProperty("csvpath")),
                BookRepository.getInstance().getBooks(), ReaderRepository.getInstance().getReaders())) {
            if ((index = ArrayWorker.searchIndex(requestRepository.getRequests(), request.getId())) != -1){
                requestRepository.getRequests().set(index, request);
            } else {
                requestRepository.add(request);
            }
        }
    }

    @Override
    public void serialize(){
        serializer.save(requestRepository.getRequests(), MyProperty.getInstance().getProperty("requestpath"));
    }

    @Override
    public void deserialize(){
        requestRepository.setRequests((List<Request>) serializer.load(MyProperty.getInstance().getProperty("requestpath")));
    }
}
