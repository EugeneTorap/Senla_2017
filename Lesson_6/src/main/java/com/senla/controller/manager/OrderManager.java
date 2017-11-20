package com.senla.controller.manager;

import com.senla.controller.repositories.BookRepository;
import com.senla.controller.repositories.OrderRepository;
import com.senla.entity.Order;
import com.senla.util.ArrayWorker;
import com.senla.util.FileWorker;
import com.senla.util.Printer;

import java.util.Comparator;
import java.util.List;

public class OrderManager {
    private OrderRepository orderRepository = new OrderRepository();
    private BookRepository bookRepository;
    private FileWorker fileWorker = new FileWorker();

    public OrderManager(BookManager bookManager) {
        this.bookRepository = bookManager.getBookRepository();
    }

    public void saveToFile(){
        fileWorker.save(orderRepository.getOrders(), "data/order.bin");
    }

    public void loadFromFile() {
        orderRepository.setOrders((List<Order>)fileWorker.load("data/order.bin"));
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
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
}
