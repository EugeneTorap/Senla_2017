package com.senla.ui.actions.order;

import com.senla.model.entity.*;
import com.senla.util.ArrayWorker;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdditionOrder implements IAction {
    @Override
    public void execute() {
        Reader reader = ArrayWorker.search(OnlineBookStore.getInstance().getReaders(), Input.nextInt("Input ID reader: "));
        List<Book> books = new ArrayList<>();
        books.add(ArrayWorker.search(OnlineBookStore.getInstance().getBooks(), Input.nextInt("Input ID book: ")));
        Date dateExecuted = Input.nextDate("Input the date executed: ");

        OnlineBookStore.getInstance().addOrder(new Order(reader, dateExecuted, books));
    }
}
