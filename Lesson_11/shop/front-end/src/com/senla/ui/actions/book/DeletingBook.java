package com.senla.ui.actions.book;

import com.senla.main.Client;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeletingBook implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        parameters.add(Input.nextInt("Input ID book: "));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("delBookFromStore", parameters);
        Client.send(request);
    }
}
