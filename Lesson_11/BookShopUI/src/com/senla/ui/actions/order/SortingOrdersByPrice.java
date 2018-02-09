package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.model.entity.Order;
import com.senla.enums.SortingType;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.printArray;

public class SortingOrdersByPrice implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.PRICE);

        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortOrdersBy", parameters);
        printArray((List<Order>) Client.send(request));
    }
}