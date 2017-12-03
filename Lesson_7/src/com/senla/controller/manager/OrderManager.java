package com.senla.controller.manager;

import com.senla.api.manager.IOrderManager;
import com.senla.controller.repositories.BookRepository;
import com.senla.controller.repositories.OrderRepository;
import com.senla.csv.Parser;
import com.senla.model.entity.Order;
import com.senla.util.*;

import java.util.Comparator;
import java.util.List;

public class OrderManager implements IOrderManager{
    private OrderRepository orderRepository;
    private Serializer serializer = new Serializer();


    public OrderManager() {
        orderRepository = OrderRepository.getInstance();
    }

    @Override
    public void add(Order order) {
        orderRepository.add(order);
    }

    @Override
    public void clone(int id) {
        orderRepository.clone(id);
    }

    @Override
    public void cancel(int id) {
        orderRepository.cancel(id);
    }

    @Override
    public int getAllPrice(){
        int sum = 0;
        for (Order order: orderRepository.getExecutedOrders()) {
            sum += order.getPrice();
        }
        return sum;
    }

    @Override
    public int getAmountExecutedOrders(){
        return orderRepository.getExecutedOrders().size();
    }

    @Override
    public List<Order> getOrders(){
        return orderRepository.getOrders();
    }

    @Override
    public List<Order> getExecutedOrders(){
        return orderRepository.getExecutedOrders();
    }

    @Override
    public void sortOrders(Comparator comparator){
        orderRepository.getOrders().sort(comparator);
    }

    @Override
    public void sortExecutedOrders(Comparator comparator){
        orderRepository.getExecutedOrders().sort(comparator);
    }

    @Override
    public void serialize(){
        serializer.save(orderRepository.getOrders(), MyProperty.getInstance().getProperty("orderpath"));
    }

    @Override
    public void deserialize() {
        orderRepository.setOrders((List<Order>) serializer.load(MyProperty.getInstance().getProperty("orderpath")));
    }
}
