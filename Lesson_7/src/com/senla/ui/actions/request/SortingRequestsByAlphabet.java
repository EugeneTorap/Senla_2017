package com.senla.ui.actions.request;

import com.senla.enums.SortingType;
import com.senla.model.entity.Book;
import com.senla.model.entity.Request;
import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;

public class SortingRequestsByAlphabet implements IAction {
    @Override
    public void execute() {
        OnlineBookStore.getInstance().sortRequestsBy(SortingType.ALPHABET);

        System.out.println();
        for (Book book : OnlineBookStore.getInstance().getBooks()) {
            Printer.print(book.toStringForRequest());
            for (Request request : OnlineBookStore.getInstance().getRequests()) {
                if (request.getBook().getId() == book.getId()) {
                    Printer.print(request.toStringForRequest());
                }
            }
            Printer.print("-----------------------");
        }
    }
}