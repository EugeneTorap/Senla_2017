package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.util.Printer;
import com.senla.model.entity.Order;
import com.senla.ui.actions.IAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPrice implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getAllPrice", null);
        Printer.print((List<Order>) Client.send(request));
    }
}