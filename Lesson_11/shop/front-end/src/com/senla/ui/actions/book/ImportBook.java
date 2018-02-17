package com.senla.ui.actions.book;

import com.senla.main.Client;
import com.senla.ui.actions.IAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportBook implements IAction {
    @Override
    public void execute() {

        Map<String, List<Object>> request = new HashMap<>();
        request.put("importBooks", null);
        Client.send(request);
    }
}