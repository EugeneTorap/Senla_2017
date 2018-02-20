package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloningOrder implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(Input.nextInt("Input ID order: "));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("cloneOrder", parameters);
        Client.send(request);
    }
}