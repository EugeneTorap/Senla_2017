package com.senla.ui.actions.order;

import com.senla.util.Printer;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;
import com.senla.ui.actions.IAction;

public class SortingOrdersByStatus implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("sortOrdersBy").create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}