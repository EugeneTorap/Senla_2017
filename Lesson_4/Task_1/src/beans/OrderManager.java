package beans;

import entity.Book;
import repositories.OrderRepository;
import entity.Order;
import util.sorter.ordersorter.*;
import util.sorter.executedordersorter.*;

import java.util.Arrays;

public class OrderManager {
private OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public int getAllPrice(){
        int sum = 0;
        for (Order order: orderRepository.getExecutedOrders()) {
            if (order != null){
                for (Book book : order.getBooks()) {
                    sum += book.getPrice();
                }
            }
        }
        return sum;
    }
    
    public int getAmountExecutedOrders(){
        int amount = 0;
        for (Order order: orderRepository.getExecutedOrders()) {
            if (order != null){
                amount++;
            }
        }
        return amount;
    }

    public Order[] sortOrdersByDate(){
        Arrays.sort(orderRepository.getOrders(), new SortingOrdersByDate());
        return orderRepository.getOrders();
    }

    public Order[] sortOrdersByPrice(){
        Arrays.sort(orderRepository.getOrders(), new SortingOrdersByPrice());
        return orderRepository.getOrders();
    }

    public Order[] sortOrdersByStatus(){
        Arrays.sort(orderRepository.getOrders(), new SortingOrdersByStatus());
        return orderRepository.getOrders();
    }

    public Order[] sortExecutedOrdersByDate(){
        Arrays.sort(orderRepository.getExecutedOrders(), new SortingExecutedOrdersByDate());
        return orderRepository.getExecutedOrders();
    }

    public Order[] sortExecutedOrdersByPrice(){
        Arrays.sort(orderRepository.getExecutedOrders(), new SortingExecutedOrdersByPrice());
        return orderRepository.getExecutedOrders();
    }
}
