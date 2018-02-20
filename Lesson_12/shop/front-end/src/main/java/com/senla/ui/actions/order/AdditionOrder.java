package com.senla.ui.actions.order;

import com.senla.api.creator.ICreator;
import com.senla.creator.Creator;
import com.senla.enums.Status;
import com.senla.main.Client;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.*;

public class AdditionOrder implements IAction {
    @Override
    public void execute() {
        ICreator creator = new Creator();
        List<Object> parameters = new ArrayList<>();

        int orderId = Input.nextInt("Input ID book: ");
        Date dateExecuted = Input.nextDate("Input the date executed: ");
        int price = Input.nextInt("Input the price: ");

        parameters.add(creator.createOrder(orderId, dateExecuted, Status.AWAITING, price));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("addOrder", parameters);
        Client.send(request);
    }
}
