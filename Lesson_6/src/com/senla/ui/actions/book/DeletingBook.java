package com.senla.ui.actions.book;

import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class DeletingBook implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().delBookFromStore(Input.nextInt("Input ID book: "));
    }
}
