package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.util.Printer;
import com.senla.enums.SortingType;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingExecutedOrdersByDate implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.DATE);
        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortExecutedOrdersBy", parameters);
        Client.send(request);
        request = new HashMap<>();
        request.put("getExecutedOrders", null);
        Printer.print((List<Order>) Client.send(request));
    }
}