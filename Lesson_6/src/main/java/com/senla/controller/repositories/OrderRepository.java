package com.senla.controller.repositories;

import com.senla.util.ArrayWorker;
import com.senla.entity.Order;
import com.senla.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orders = new ArrayList<>();


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getExecutedOrders(){
        List<Order> executedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.isExecuted()) {
                executedOrders.add(order);
            }
        }
        return executedOrders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void cloneOrder(int id) {
        Order order1 = ArrayWorker.search(orders, id);
        assert order1 != null;
        Order order2 = order1.clone();
        orders.add(order2);
    }

    public void cancelOrder(int id){
        int index = ArrayWorker.searchIndex(orders, id);
        if (index != -1){
            orders.get(index).setStatus(Status.CANCELED);
            return;
        }
        System.out.println("There's no such order");
    }
}
