package com.senla.ui.actions.order;

import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class AllPrice implements IAction {
    @Override
    public void execute() {
        Printer.print(OnlineBookStore.getInstance().getAllPrice());
    }
}