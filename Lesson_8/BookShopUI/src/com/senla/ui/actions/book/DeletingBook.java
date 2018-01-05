package com.senla.ui.actions.book;

import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class DeletingBook implements IAction {
    @Override
    public void execute() {
        int id = Input.nextInt("Input ID book: ");
        client.sendRequest(new RequestCreator().setMethod("delBookFromStore").setArgument(id).create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }

}
