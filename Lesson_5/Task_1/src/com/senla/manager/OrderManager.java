package com.senla.manager;

import com.senla.repositories.BookRepository;
import com.senla.repositories.OrderRepository;
import com.senla.entity.Order;
import com.senla.util.ArrayWorker;
import com.senla.util.FileWorker;
import com.senla.util.Printer;

import java.text.ParseException;
import java.util.Comparator;

public class OrderManager {
    private OrderRepository orderRepository = new OrderRepository();
    private BookRepository bookRepository;
    private FileWorker fileWorker = new FileWorker();

    public OrderManager(BookManager bookManager) {
        this.bookRepository = bookManager.getBookRepository();
    }

    public void saveToFile(){
        fileWorker.save(orderRepository.getOrders(), "order.txt");
    }

    public void loadFromFile() {
        try {
            orderRepository.setOrders(fileWorker.loadOrders("order.txt", bookRepository.getBooks()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void cancelOrder(int id) {
        orderRepository.cancelOrder(id);
    }

    public void showOrderInfo(int id) {
        Printer.print(ArrayWorker.searchOrder(orderRepository.getOrders(), id));
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
