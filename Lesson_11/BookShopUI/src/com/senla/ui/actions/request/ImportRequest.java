package com.senla.ui.actions.request;

import com.senla.main.Client;
import com.senla.ui.actions.IAction;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportRequest implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        parameters.add(Book.class);
        Map<String, List<Object>> request = new HashMap<>();
        request.put("loadCSV", parameters);
        Client.send(request);
    }
}