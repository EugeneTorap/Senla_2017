package com.senla.controller.repositories;

import com.senla.api.repository.IRequestRepository;
import com.senla.entity.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestRepository implements IRequestRepository {
    private List<Request> requests = new ArrayList<>();
    private static volatile RequestRepository instance = null;


    public static RequestRepository getInstance() {
        if (instance == null) {
            synchronized (RequestRepository.class){
                if (instance == null) {
                    instance = new RequestRepository();
                }
            }
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
