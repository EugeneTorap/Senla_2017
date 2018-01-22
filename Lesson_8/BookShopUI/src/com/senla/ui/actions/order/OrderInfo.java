package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.model.entity.Order;
import com.senla.util.ArrayWorker;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.print;

public class OrderInfo implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getOrders", null);
        Order order = ArrayWorker.search((List<Order>) Client.send(request), Input.nextInt("Input ID order: "));
        if (order != null){
            print(order);
            return;
        }
        print("There's no such order");
    }
}