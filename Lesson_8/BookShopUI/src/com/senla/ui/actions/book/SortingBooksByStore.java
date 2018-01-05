package com.senla.ui.actions.book;

import com.senla.util.Printer;
import com.senla.util.RequestCreator;
import com.senla.util.ServerResponse;
import com.senla.enums.SortingType;
import com.senla.ui.actions.IAction;

public class SortingBooksByStore implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("sortBooksBy").setArgument(SortingType.IS_STORE).create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}