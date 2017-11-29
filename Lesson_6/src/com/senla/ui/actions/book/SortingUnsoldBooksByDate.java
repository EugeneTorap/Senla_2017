package com.senla.ui.actions.book;

import com.senla.enums.SortingType;
import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingUnsoldBooksByDate implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().sortUnsoldBooksBy(SortingType.DATE);
        Printer.printArray(OnlineBookStore.getInstance().getUnsoldBooks());
    }
}