package com.senla.ui.actions.order;

import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class CancellationOrder implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().cancelOrder(Input.nextInt("Input ID order: "));
    }
}