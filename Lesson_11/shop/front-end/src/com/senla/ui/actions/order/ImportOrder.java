package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.ui.actions.IAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportOrder implements IAction {
    @Override
    public void execute() {

        Map<String, List<Object>> request = new HashMap<>();
        request.put("loadCSV", null);
        Client.send(request);
    }
}
