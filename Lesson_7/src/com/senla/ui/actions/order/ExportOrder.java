package com.senla.ui.actions.order;

import com.senla.csv.CSVWorker;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExportOrder implements IAction {
    @Override
    public void execute() {
        CSVWorker.save(OnlineBookStore.getInstance().getOrders());
    }
}
