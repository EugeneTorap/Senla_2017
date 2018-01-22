package com.senla.ui.actions.request;

import com.senla.main.Client;
import com.senla.model.entity.Book;
import com.senla.model.entity.Reader;
import com.senla.model.entity.Request;
import com.senla.util.ArrayWorker;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.*;

public class AdditionRequest implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getReaders", null);
        Reader reader = ArrayWorker.search((List<Reader>) Client.send(request), Input.nextInt("Input ID reader: "));
        request = new HashMap<>();
        request.put("getBooks", null);
        Book book = ArrayWorker.search((List<Book>) Client.send(request), Input.nextInt("Input ID book: "));
        List<Object> parameters = new ArrayList<>();
        parameters.add(new Request(book, reader));
        request = new HashMap<>();
        request.put("addRequest", parameters);
        Client.send(request);
    }
}
