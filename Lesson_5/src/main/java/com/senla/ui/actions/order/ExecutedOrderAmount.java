package com.senla.ui.actions.order;

import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExecutedOrderAmount implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().showAmountExecutedOrders();
    }
}