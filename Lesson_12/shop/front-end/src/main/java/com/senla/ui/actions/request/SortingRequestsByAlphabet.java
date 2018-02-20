package com.senla.ui.actions.request;

import com.senla.api.model.IBook;
import com.senla.enums.SortingType;
import com.senla.main.Client;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.printArray;

public class SortingRequestsByAlphabet implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.ALPHABET);

        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortRequestsBy", parameters);
        printArray((List<IBook>) Client.send(request));
    }
}