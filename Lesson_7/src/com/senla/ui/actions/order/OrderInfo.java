package com.senla.ui.actions.order;

import com.senla.model.entity.Order;
import com.senla.util.ArrayWorker;
import com.senla.util.Printer;
import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.actions.IAction;
import com.senla.util.Input;

public class OrderInfo implements IAction {
    @Override
    public void execute() {
        Order order = ArrayWorker.search(OnlineBookStore.getInstance().getOrders(), Input.nextInt("Input ID order: "));
        if (order != null){
            Printer.print(order);
            return;
        }
        Printer.print("There's no such order");
    }
}