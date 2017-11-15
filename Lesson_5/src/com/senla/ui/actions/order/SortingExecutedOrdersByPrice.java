package com.senla.ui.actions.order;

import com.senla.enums.SortingType;
import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingExecutedOrdersByPrice implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().showExecutedOrdersSortedBy(SortingType.PRICE);
    }
}