package com.senla.controller.repositories;

import com.senla.api.repository.IRequestRepository;
import com.senla.model.entity.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestRepository implements IRequestRepository {
    private List<Request> requests;
    private static RequestRepository instance = null;


    public RequestRepository() {
        this.requests = new ArrayList<>();
    }

    public static RequestRepository getInstance() {
        if (instance == null) {
            instance = new RequestRepository();
        }
        return instance;
    }

    @Override
    public void add(Request request) {
        requests.add(request);
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
