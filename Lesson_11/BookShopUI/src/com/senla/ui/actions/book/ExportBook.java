package com.senla.ui.actions.book;

import com.senla.main.Client;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportBook implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getBooks", null);
        List<Object> parameters = new ArrayList<>();
        parameters.add(Client.send(request));
        request = new HashMap<>();
        request.put("saveCSV", parameters);
        Client.send(request);
    }
}