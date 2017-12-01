package com.senla.ui.actions.request;

import com.senla.model.entity.Book;
import com.senla.model.entity.Reader;
import com.senla.model.entity.Request;
import com.senla.util.ArrayWorker;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class AdditionRequest implements IAction {
    @Override
    public void execute() {
        Reader reader = ArrayWorker.search(OnlineBookStore.getInstance().getReaders(), Input.nextInt("Input ID reader: "));
        Book book = ArrayWorker.search(OnlineBookStore.getInstance().getBooks(), Input.nextInt("Input ID book: "));
        OnlineBookStore.getInstance().addRequest(new Request(book, reader));
    }
}
