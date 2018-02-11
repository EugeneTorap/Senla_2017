package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.model.entity.Book;
import com.senla.model.entity.Order;
import com.senla.util.ArrayWorker;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.print;

public class OrderInfo implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(Input.nextInt("Input ID order: "));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("getOrder", parameters);
        print((Book) Client.send(request));
    }
}