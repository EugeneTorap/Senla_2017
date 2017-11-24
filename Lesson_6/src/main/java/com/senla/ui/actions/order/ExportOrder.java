package com.senla.ui.actions.order;

import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExportOrder implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().exportOrder();
    }
}
