package com.senla.ui.actions.request;

import com.senla.main.Client;
import com.senla.util.Printer;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingRequestsByAlphabet implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.ALPHABET);
        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortRequestsBy", parameters);
        Client.send(request);

        request = new HashMap<>();
        request.put("getRequests", null);
        Printer.print((List<Request>) Client.send(request));
    }
}