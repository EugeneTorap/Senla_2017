package com.senla.ui.actions.request;

import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExportRequest implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().exportRequest();
    }
}