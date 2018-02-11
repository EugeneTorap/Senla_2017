package com.senla.ui.actions.request;

import com.senla.enums.SortingType;
import com.senla.main.Client;
import com.senla.model.entity.Book;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.printArray;

public class SortingRequestsByAmount implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.AMOUNT);

        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortRequestsBy", parameters);
        printArray((List<Book>) Client.send(request));
    }
}