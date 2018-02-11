package com.senla.ui.actions.order;

import com.senla.enums.Status;
import com.senla.main.Client;
import com.senla.model.entity.*;
import com.senla.util.ArrayWorker;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.*;

public class AdditionOrder implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Reader> readers;

        int bookId;
        String title;
        int price;
        Date datePublished;
        Date dateReceipted;

        String answer;
        do {
            bookId = Input.nextInt("Input ID book: ");
            title = Input.nextLine("Input the title: ");
            price = Input.nextInt("Input the price: ");
            datePublished = Input.nextDate("Input the date published: ");
            dateReceipted = Input.nextDate("Input the date receipted: ");
            readers = new ArrayList<>();

            do {
                int readerId = Input.nextInt("Input ID reader: ");
                String readerName = Input.nextLine("Input the reader's name: ");
                readers.add(new Reader(readerId, readerName));
                answer = Input.nextLine("Can you add yet reader?\n 1 -- yes\n 2 -- no\nPlease write answer: ");
            } while (answer.equals("1"));

            books.add(new Book(bookId, title, price, false, datePublished, dateReceipted, readers));

            answer = Input.nextLine("Can you add yet book?\n 1 -- yes\n 2 -- no\nPlease write answer: ");
        } while (answer.equals("1"));

        int orderId = Input.nextInt("Input ID book: ");
        Date dateExecuted = Input.nextDate("Input the date executed: ");

        int readerId = Input.nextInt("Input ID reader: ");
        String readerName = Input.nextLine("Input the reader's name: ");

        parameters.add(new Order(orderId, new Reader(readerId, readerName), dateExecuted, books, Status.AWAITING));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("addOrder", parameters);
        Client.send(request);
    }
}
