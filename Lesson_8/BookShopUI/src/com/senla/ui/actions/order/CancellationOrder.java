package com.senla.ui.actions.order;

import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class CancellationOrder implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().setMethod("cancelOrder").setArgument(Input.nextInt("Input ID order: ")).create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}