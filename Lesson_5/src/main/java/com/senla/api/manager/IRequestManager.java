package com.senla.api.manager;

import com.senla.entity.Request;

public interface IRequestManager extends IManager {
    void add(Request request);
    void showBookRequests();
    void setRequestAmount();
}
