package com.senla.api.repository;

import com.senla.entity.Request;

import java.util.List;

public interface IRequestRepository {
    void add(Request request);
    List<Request> getRequests();
    void setRequests(List<Request> requests);
}
