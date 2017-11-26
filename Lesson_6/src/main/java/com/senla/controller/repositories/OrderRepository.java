package com.senla.controller.repositories;

import com.senla.api.repository.IOrderRepository;
import com.senla.util.ArrayWorker;
import com.senla.entity.Order;
import com.senla.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private List<Order> orders = new ArrayList<>();
    private static volatile OrderRepository instance = null;


    public static OrderRepository getInstance() {
        if (instance == null) {
            synchronized (OrderRepository.class){
                if (instance == null) {
                    instance = new OrderRepository();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        orders.add(order);
    }

    @Override
    public void cancel(int id) {
        int index = ArrayWorker.searchIndex(orders, id);
        if (index != -1){
            orders.get(index).setStatus(Status.CANCELED);
            return;
        }
        System.out.println("There's no such order");
    }

    @Override
    public void clone(int id) {
        Order order = ArrayWorker.search(orders, id);
        assert order != null;
        try {
            orders.add(order.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public List<Order> getExecutedOrders(){
        List<Order> executedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.isExecuted()) {
                executedOrders.add(order);
            }
        }
        return executedOrders;
    }
}
