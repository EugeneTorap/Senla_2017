package com.senla.ui.actions.request;

import com.senla.csv.CSVWorker;
import com.senla.model.entity.Request;
import com.senla.ui.actions.IAction;

public class ImportRequest implements IAction {
    @Override
    public void execute() {
        CSVWorker.load(Request.class);
    }
}