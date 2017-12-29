package com.senla.util;

import java.io.Serializable;
import java.util.List;

public class ClientRequest implements Serializable {

    private List<Object> arguments;
    private String method;

    public ClientRequest(String method, List<Object> arguments) {
        this.method = method;
        this.arguments = arguments;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Object> getArguments() {
        return arguments;
    }

}
