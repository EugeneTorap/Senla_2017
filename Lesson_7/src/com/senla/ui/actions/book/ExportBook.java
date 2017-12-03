package com.senla.ui.actions.book;

import com.senla.csv.CSVWorker;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class ExportBook implements IAction {
    @Override
    public void execute() {
        CSVWorker.save(OnlineBookStore.getInstance().getBooks());
    }
}