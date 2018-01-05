package com.senla.ui.actions.order;

import com.senla.util.Printer;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;
import com.senla.enums.SortingType;
import com.senla.ui.actions.IAction;

public class SortingOrdersByPrice implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("sortOrdersBy").setArgument(SortingType.PRICE).create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}