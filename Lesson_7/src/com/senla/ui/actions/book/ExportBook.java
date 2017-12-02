package com.senla.ui.actions.book;

import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExportBook implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().exportBook();
    }
}