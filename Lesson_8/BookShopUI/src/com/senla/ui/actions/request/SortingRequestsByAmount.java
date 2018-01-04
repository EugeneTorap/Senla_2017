package com.senla.ui.actions.request;

import com.senla.util.Printer;
import com.senla.ui.actions.IAction;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;

public class SortingRequestsByAmount implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("sortRequestsBy").create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}