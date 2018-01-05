package com.senla.ui.actions.request;

import com.senla.util.Printer;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;
import com.senla.ui.actions.IAction;

public class SortingRequestsByAlphabet implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("sortRequestsBy").setArgument(SortingType.ALPHABET).create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}