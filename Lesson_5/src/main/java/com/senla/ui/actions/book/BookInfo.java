package com.senla.ui.actions.book;

import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class BookInfo implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().showBookInfo(Input.nextInt("Input ID book: "));
    }
}