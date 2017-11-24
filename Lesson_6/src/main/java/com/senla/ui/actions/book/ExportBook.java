package com.senla.ui.actions.book;

import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExportBook implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().exportBook();
    }
}