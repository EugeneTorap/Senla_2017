package com.senla.ui.actions.order;

import com.senla.enums.SortingType;
import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingOrdersByPrice implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().sortOrdersBy(SortingType.PRICE);
        Printer.printArray(OnlineBookStore.getInstance().getOrders());
    }
}