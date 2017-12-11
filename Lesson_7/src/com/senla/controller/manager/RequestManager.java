package com.senla.controller.manager;

import com.senla.api.manager.IRequestManager;
import com.senla.dependencyinjection.DependencyInjection;
import com.senla.model.entity.Book;
import com.senla.controller.repositories.*;
import com.senla.model.entity.Request;
import com.senla.util.*;
import java.util.List;

public class RequestManager implements IRequestManager{
    private RequestRepository requestRepository;
    private Serializer serializer = new Serializer();


    public RequestManager() {
        requestRepository = (RequestRepository) DependencyInjection.getInstance().getObject(RequestRepository.class);
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
    public void serialize(){
        serializer.save(requestRepository.getRequests(), MyProperty.getInstance().getProperty("requestpath"));
    }

    @Override
    public void deserialize(){
        requestRepository.setRequests((List<Request>) serializer.load(MyProperty.getInstance().getProperty("requestpath")));
    }
}
