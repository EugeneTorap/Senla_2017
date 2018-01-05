package com.senla.ui.actions.book;

import com.senla.model.entity.Book;
import com.senla.ui.actions.IAction;

public class ExportBook implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("getBooks").create());
        ServerResponse response1 = client.getResponse();
        List<Book> books = response1.getResponse();
        client.sendRequest(new RequestCreator().setMethod("save").setArgument(books).create());
        ServerResponse response2 = client.getResponse();
        Printer.print(response2.getResponse());
    }
}