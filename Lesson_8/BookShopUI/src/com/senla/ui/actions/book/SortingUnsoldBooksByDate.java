package com.senla.ui.actions.book;

import com.senla.main.Client;
import com.senla.util.Printer;
import com.senla.enums.SortingType;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingUnsoldBooksByDate implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.DATE);
        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortUnsoldBooksBy", parameters);
        Client.send(request);
        request = new HashMap<>();
        request.put("getUnsoldBooks", null);
        Printer.print((List<Book>) Client.send(request));
    }
}