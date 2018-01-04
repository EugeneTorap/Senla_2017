package com.senla.ui.actions.book;

import com.senla.util.Printer;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;
import com.senla.ui.actions.IAction;

public class SortingUnsoldBooksByDate implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("sortUnsoldBooksBy").create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}