package com.senla.ui.actions.book;

import com.senla.model.entity.Book;
import com.senla.ui.actions.IAction;

public class ImportBook implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("load").setArgument(Book.class).create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}