package com.senla.ui.actions.order;

import com.senla.csv.CSVWorker;
import com.senla.main.Client;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportOrder implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getOrders", null);
        List<Object> parameters = new ArrayList<>();
        parameters.add(Client.send(request));
        request = new HashMap<>();
        request.put("saveCSV", parameters);
        Client.send(request);
    }
}
