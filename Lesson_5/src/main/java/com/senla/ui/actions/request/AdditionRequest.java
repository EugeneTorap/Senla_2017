package com.senla.ui.actions.request;

import com.senla.entity.Book;
import com.senla.entity.Reader;
import com.senla.entity.Request;
import com.senla.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class AdditionRequest implements IAction {
    @Override
    public void execute() {
        Reader reader = OnlineBookStore.getInstance().searchReader(Input.nextInt("Input ID reader: "));
        Book book = OnlineBookStore.getInstance().searchBook(Input.nextInt("Input ID book: "));
        OnlineBookStore.getInstance().addRequest(new Request(book, reader));
    }
}
