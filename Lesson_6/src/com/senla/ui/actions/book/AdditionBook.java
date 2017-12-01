package com.senla.ui.actions.book;

import com.senla.model.entity.Book;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

import java.util.Date;

public class AdditionBook implements IAction {
    @Override
    public void execute() {
        String title = Input.nextLine("Input the title: ");
        Integer price = Input.nextInt("Input the price: ");
        Date datePublished = Input.nextDate("Input the date published: ");
        Date dateReceipted = Input.nextDate("Input the date receipted: ");
        OnlineBookStore.getInstance().addBook(new Book(title, price, datePublished, dateReceipted));
    }
}