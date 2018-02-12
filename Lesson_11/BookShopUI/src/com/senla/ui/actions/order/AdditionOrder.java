package com.senla.ui.actions.order;

import com.senla.enums.Status;
import com.senla.main.Client;
import com.senla.model.entity.*;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.*;

public class AdditionOrder implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();

        int orderId = Input.nextInt("Input ID book: ");
        Date dateExecuted = Input.nextDate("Input the date executed: ");
        int price = Input.nextInt("Input the price: ");
        int readerId = Input.nextInt("Input ID reader: ");
        String readerName = Input.nextLine("Input the reader's name: ");

        parameters.add(new Order(orderId, new Reader(readerId, readerName), dateExecuted, Status.AWAITING, price));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("addOrder", parameters);
        Client.send(request);
    }
}
