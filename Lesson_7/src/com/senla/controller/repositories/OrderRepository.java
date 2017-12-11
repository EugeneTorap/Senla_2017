package com.senla.controller.repositories;

import com.senla.api.repository.IOrderRepository;
import com.senla.util.ArrayWorker;
import com.senla.model.entity.Order;
import com.senla.enums.Status;
import com.senla.util.Printer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private List<Order> orders;
    private static OrderRepository instance = null;
    private final static Logger LOGGER = Logger.getLogger(OrderRepository.class);


    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
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
        Printer.print("There's no such order");
    }

    @Override
    public void clone(int id) {
        Order order = ArrayWorker.search(orders, id);
        assert order != null;
        try {
            orders.add(order.clone());
        } catch (CloneNotSupportedException e) {
            LOGGER.error(e.getMessage());
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
