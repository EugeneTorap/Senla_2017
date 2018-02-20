package com.senla.ui.actions.order;

import com.senla.api.model.IOrder;
import com.senla.main.Client;
import com.senla.enums.SortingType;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.printArray;

public class SortingExecutedOrdersByDate implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.DATE);

        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortExecutedOrdersBy", parameters);
        printArray((List<IOrder>) Client.send(request));
    }
}