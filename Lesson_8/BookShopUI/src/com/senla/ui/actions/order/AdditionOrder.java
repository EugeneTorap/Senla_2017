package com.senla.ui.actions.order;

import com.senla.main.Client;
import com.senla.model.entity.*;
import com.senla.util.ArrayWorker;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.*;

public class AdditionOrder implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getReaders", null);
        Reader reader = ArrayWorker.search((List<Reader>) Client.send(request), Input.nextInt("Input ID reader: "));
        List<Book> books = new ArrayList<>();
        request = new HashMap<>();
        request.put("getBooks", null);
        books.add(ArrayWorker.search((List<Book>) Client.send(request), Input.nextInt("Input ID book: ")));
        Date dateExecuted = Input.nextDate("Input the date executed: ");
        List<Object> parameters = new ArrayList<>();
        parameters.add(new Order(reader, dateExecuted, books));
        request = new HashMap<>();
        request.put("addBook", parameters);
        Client.send(request);
    }
}
