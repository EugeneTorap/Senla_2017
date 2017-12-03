package com.senla.ui.actions.book;

import com.senla.csv.CSVWorker;
import com.senla.model.entity.Book;
import com.senla.ui.actions.IAction;

public class ImportBook implements IAction {
    @Override
    public void execute() {
        CSVWorker.load(Book.class);
    }
}