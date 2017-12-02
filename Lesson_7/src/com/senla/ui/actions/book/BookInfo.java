package com.senla.ui.actions.book;

import com.senla.model.entity.Book;
import com.senla.util.ArrayWorker;
import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class BookInfo implements IAction {
    @Override
    public void execute() {
        Book book = ArrayWorker.search(OnlineBookStore.getInstance().getBooks(), Input.nextInt("Input ID book: "));
        if (book != null){
            Printer.print(book);
            return;
        }
        Printer.print("There's no such book");
    }
}