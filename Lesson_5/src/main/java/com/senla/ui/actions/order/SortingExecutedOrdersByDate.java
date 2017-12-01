package com.senla.ui.actions.order;

import com.senla.enums.SortingType;
import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingExecutedOrdersByDate implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().showExecutedOrdersSortedBy(SortingType.DATE);
    }
}