package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.util.Printer;
import com.senla.enums.SortingType;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingOrdersByStatus implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.STATUS);
        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortOrdersBy", parameters);
        Client.send(request);

        request = new HashMap<>();
        request.put("getOrders", null);
        Printer.print((List<Order>) Client.send(request));
    }
}