package com.senla.util;

import java.util.ArrayList;
import java.util.List;

public class RequestCreator {

    private List<Object> arguments = new ArrayList<>();
    private String method;

    public RequestCreator setMethod(String method) {
        this.method = method;
        return this;
    }

    public RequestCreator setArgument(Object argument) {
        arguments.add(argument);
        return this;
    }

    public ClientRequest create() {
        return new ClientRequest(method, arguments);
    }
}