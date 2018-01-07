package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.model.entity.Order;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportOrder implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        parameters.add(Order.class);
        Map<String, List<Object>> request = new HashMap<>();
        request.put("loadCSV", parameters);
        Client.send(request);
    }
}
