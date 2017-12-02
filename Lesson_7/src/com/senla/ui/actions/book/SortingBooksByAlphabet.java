package com.senla.ui.actions.book;

import com.senla.enums.SortingType;
import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingBooksByAlphabet implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().sortBooksBy(SortingType.ALPHABET);
        Printer.printArray(OnlineBookStore.getInstance().getBooks());
    }
}