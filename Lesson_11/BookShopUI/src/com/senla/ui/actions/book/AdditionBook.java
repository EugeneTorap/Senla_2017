package com.senla.ui.actions.book;

import com.senla.main.Client;
import com.senla.model.entity.Reader;
import com.senla.ui.actions.IAction;
import com.senla.model.entity.Book;
import com.senla.util.Input;

import java.util.*;

public class AdditionBook implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();

        int bookId = Input.nextInt("Input ID book: ");
        String title = Input.nextLine("Input the title: ");
        int price = Input.nextInt("Input the price: ");
        Date datePublished = Input.nextDate("Input the date published: ");
        Date dateReceipted = Input.nextDate("Input the date receipted: ");
        List<Reader> requests = new ArrayList<>();

        String answer;
        do {
            int readerId = Input.nextInt("Input ID reader: ");
            String readerName = Input.nextLine("Input the reader's name: ");
            requests.add(new Reader(readerId, readerName));
            answer = Input.nextLine("Can you add yet reader?\n 1 -- yes\n 2 -- no\nPlease write answer: ");
        } while (answer.equals("1"));

        parameters.add(new Book(bookId, title, price, false, datePublished, dateReceipted, requests));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("addBook", parameters);
        Client.send(request);
    }
}