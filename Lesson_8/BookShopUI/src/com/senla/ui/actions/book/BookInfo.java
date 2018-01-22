package com.senla.ui.actions.book;

import com.senla.main.Client;
import com.senla.model.entity.Book;
import com.senla.util.ArrayWorker;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.util.Printer.print;

public class BookInfo implements IAction {
    @Override
    public void execute() {
        Map<String, List<Object>> request = new HashMap<>();
        request.put("getBooks", null);
        Book book = ArrayWorker.search((List<Book>) Client.send(request), Input.nextInt("Input ID book: "));
        if (book != null){
            print(book);
            return;
        }
        print("There's no such book");
    }
}