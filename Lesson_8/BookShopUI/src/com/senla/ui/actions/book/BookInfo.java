package com.senla.ui.actions.book;

import com.senla.main.Client;
import com.senla.model.entity.Book;
import com.senla.util.ArrayWorker;
import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookInfo implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getBooks", null);
        Book book = ArrayWorker.search((List<Book>) Client.send(request), Input.nextInt("Input ID book: "));
        if (book != null){
            Printer.print(book);
            return;
        }
        Printer.print("There's no such book");
    }
}