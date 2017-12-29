package com.senla.ui.actions.order;

import com.senla.util.Printer;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;
import com.senla.ui.actions.IAction;

public class SortingExecutedOrdersByDate implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("sortExecutedOrdersBy").create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}