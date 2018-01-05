package com.senla.ui.actions.order;

import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class CloningOrder implements IAction {
    @Override
    public void execute() {
        client.sendRequest(new RequestCreator().cloneOrder("cancelOrder").setArgument(Input.nextInt("Input ID order: ")).create());
        ServerResponse response = client.getResponse();
        Printer.print(response.getResponse());
    }
}