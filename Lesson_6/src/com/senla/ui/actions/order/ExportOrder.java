package com.senla.ui.actions.order;

import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExportOrder implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().exportOrder();
    }
}
