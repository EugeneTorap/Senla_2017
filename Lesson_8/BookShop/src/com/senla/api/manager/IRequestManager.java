package com.senla.api.manager;

import com.senla.model.entity.Request;

import java.util.List;

public interface IRequestManager extends IManager {
    void add(Request request);
    List<Request> getRequests();
    void setRequestAmount();
}
