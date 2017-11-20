package com.senla.ui.actions.order;

import com.senla.entity.*;
import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdditionOrder implements IAction {
    @Override
    public void execute() {
        Reader reader = OnlineBookStore.getInstance().searchReader(Input.nextInt("Input ID reader: "));;
        List<Book> books = new ArrayList<>();
        books.add(OnlineBookStore.getInstance().searchBook(Input.nextInt("Input ID book: ")));
        Date dateExecuted = Input.nextDate("Input the date executed: ");

        OnlineBookStore.getInstance().addOrder(new Order(reader, dateExecuted, books));
    }
}
