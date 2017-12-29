package com.senla.ui.actions;

import com.senla.main.Client;
import com.senla.util.ClientRequest;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;

public interface IAction {
    Client client = new Client("localhost", 1234);
    ClientRequest request = new RequestCreator().create();
    ServerResponse response = new ServerResponse();
    void execute();
}
