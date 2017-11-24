package com.senla.controller.manager;

import com.senla.controller.repositories.BookRepository;
import com.senla.controller.repositories.OrderRepository;
import com.senla.entity.Order;
import com.senla.util.*;

import java.util.Comparator;
import java.util.List;

public class OrderManager {
    private OrderRepository orderRepository;
    private Serializer serializer = new Serializer();


    public OrderManager() {
        orderRepository = OrderRepository.getInstance();
    }

    public void saveToFile(){
        serializer.save(orderRepository.getOrders(), MyProperty.getInstance().getProperty("orderpath"));
    }

    public void loadFromFile() {
        orderRepository.setOrders((List<Order>) serializer.load(MyProperty.getInstance().getProperty("orderpath")));
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void cloneOrder(int id){
        orderRepository.cloneOrder(id);
    }

    public void cancelOrder(int id) {
        orderRepository.cancelOrder(id);
    }

    public void showOrderInfo(int id) {
        Order order = ArrayWorker.search(orderRepository.getOrders(), id);
        if (order != null){
            Printer.print(order);
            return;
        }
        System.out.println("There's no such order");
    }

    public int getAllPrice(){
        int sum = 0;
        for (Order order: orderRepository.getExecutedOrders()) {
            sum += order.getPrice();
        }
        return sum;
    }
    
    public int getAmountExecutedOrders(){
        return orderRepository.getExecutedOrders().size();
    }

    public void showOrders(){
        Printer.printArray(orderRepository.getOrders());
    }

    public void showExecutedOrders(){
        Printer.printArray(orderRepository.getExecutedOrders());
    }

    public void sortOrders(Comparator comparator){
        orderRepository.getOrders().sort(comparator);
    }

    public void sortExecutedOrders(Comparator comparator){
        orderRepository.getExecutedOrders().sort(comparator);
    }

    public void exportOrder() {
        FileWorker.save(orderRepository.getOrders(), MyProperty.getInstance().getProperty("csvpath"));
    }

    public void importOrder() {
        int index;
        for (Order order : Parser.parseOrder(FileWorker.load(MyProperty.getInstance().getProperty("csvpath")),
                BookRepository.getInstance().getBooks())) {
            if ((index = ArrayWorker.searchIndex(orderRepository.getOrders(), order.getId())) != -1){
                orderRepository.getOrders().set(index, order);
            } else {
                orderRepository.addOrder(order);
            }
        }
    }
}
