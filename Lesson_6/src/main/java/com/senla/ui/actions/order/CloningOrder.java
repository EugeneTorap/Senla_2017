package com.senla.ui.actions.order;

import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class CloningOrder implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().cloneOrder(Input.nextInt("Input ID order: "));
    }
}