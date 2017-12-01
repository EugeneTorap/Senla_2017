package beans;

import repositories.OrderRepository;
import entity.Order;
import comparator.order.*;

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
            sum += order.getPrice();
        }
        return sum;
    }
    
    public int getAmountExecutedOrders(){
        return orderRepository.getExecutedOrders().length;
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
        orderRepository.updateExecutedOrders();
        Arrays.sort(orderRepository.getExecutedOrders(), new SortingOrdersByDate());
        return orderRepository.getExecutedOrders();
    }

    public Order[] sortExecutedOrdersByPrice(){
        orderRepository.updateExecutedOrders();
        Arrays.sort(orderRepository.getExecutedOrders(), new SortingOrdersByPrice());
        return orderRepository.getExecutedOrders();
    }
}
