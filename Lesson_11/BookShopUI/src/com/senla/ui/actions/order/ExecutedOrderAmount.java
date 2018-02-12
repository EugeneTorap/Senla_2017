package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.model.entity.Order;
import com.senla.ui.actions.IAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.print;

public class ExecutedOrderAmount implements IAction {
    @Override
    public void execute() {

        Map<String, List<Object>> request = new HashMap<>();
        request.put("getAmountExecutedOrders", null);
        print(Client.send(request));
    }
}