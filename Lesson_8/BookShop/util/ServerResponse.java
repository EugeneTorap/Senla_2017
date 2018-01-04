package com.senla.util;

import com.senla.enums.ResponseStatus;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private ResponseStatus status;
    private String response;

    public ServerResponse() {
    }

    public ServerResponse(ResponseStatus status, String response) {
        this.status = status;
        this.response = response;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
