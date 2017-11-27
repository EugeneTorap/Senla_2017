package com.senla.controller.manager;

import com.senla.api.manager.IOrderManager;
import com.senla.api.repository.IOrderRepository;
import com.senla.controller.repositories.BookRepository;
import com.senla.controller.repositories.OrderRepository;
import com.senla.entity.Order;
import com.senla.util.*;

import java.util.Comparator;
import java.util.List;

public class OrderManager implements IOrderManager{
    private OrderRepository orderRepository;


    public OrderManager() {
        orderRepository = OrderRepository.getInstance();
    }

    @Override
    public void add(Order order) {
        orderRepository.add(order);
    }

    @Override
    public void cancel(int id) {
        orderRepository.cancel(id);
    }

    @Override
    public void showOrderInfo(int id) {
        Order order = ArrayWorker.search(orderRepository.getOrders(), id);
        if (order != null){
            Printer.print(order);
            return;
        }
        Printer.print("There's no such order");
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
    public void showOrders(){
        Printer.printArray(orderRepository.getOrders());
    }

    @Override
    public void showExecutedOrders(){
        Printer.printArray(orderRepository.getExecutedOrders());
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
    public void exportToFile() {
        FileWorker.save(orderRepository.getOrders(), "data/order.txt");
    }

    @Override
    public void importFromFile() {
        int index;
        for (Order order : Parser.parseOrder(FileWorker.load("data/order.txt"),
                BookRepository.getInstance().getBooks())) {
            if ((index = ArrayWorker.searchIndex(orderRepository.getOrders(), order.getId())) != -1){
                orderRepository.getOrders().set(index, order);
            } else {
                orderRepository.add(order);
            }
        }
    }
}
