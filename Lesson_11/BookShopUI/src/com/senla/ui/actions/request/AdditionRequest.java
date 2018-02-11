package com.senla.ui.actions.request;

import com.senla.main.Client;
import com.senla.model.entity.Book;
import com.senla.model.entity.Reader;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.*;

public class AdditionRequest implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(Input.nextInt("Input ID reader: "));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("getReader", parameters);
        Reader reader = (Reader) Client.send(request);

        parameters = new ArrayList<>();
        parameters.add(Input.nextInt("Input ID book: "));

        request = new HashMap<>();
        request.put("getBook", parameters);
        Book book = (Book) Client.send(request);

        parameters = new ArrayList<>();
        parameters.add(book);
        parameters.add(reader);
        request = new HashMap<>();
        request.put("addRequest", parameters);
        Client.send(request);
    }
}
