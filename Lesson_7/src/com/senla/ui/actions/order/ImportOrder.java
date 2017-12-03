package com.senla.ui.actions.order;

import com.senla.csv.CSVWorker;
import com.senla.model.entity.Order;
import com.senla.ui.actions.IAction;

public class ImportOrder implements IAction {
    @Override
    public void execute() {
        CSVWorker.load(Order.class);
    }
}
