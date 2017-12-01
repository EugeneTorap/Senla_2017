package com.senla.ui.actions.book;

import com.senla.enums.SortingType;
import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingBooksByPrice implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().showBooksSortedBy(SortingType.PRICE);
    }
}