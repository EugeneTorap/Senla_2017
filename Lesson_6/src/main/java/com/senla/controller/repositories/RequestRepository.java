package com.senla.controller.repositories;

import com.senla.entity.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestRepository {
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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request){
        requests.add(request);
    }
}
