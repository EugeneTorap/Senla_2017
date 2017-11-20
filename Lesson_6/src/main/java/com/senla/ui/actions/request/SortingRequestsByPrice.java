package com.senla.ui.actions.request;

import com.senla.enums.SortingType;
import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingRequestsByPrice implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().showRequestsSortedBy(SortingType.PRICE);
    }
}