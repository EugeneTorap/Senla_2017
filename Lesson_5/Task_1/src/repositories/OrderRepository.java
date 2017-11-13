package repositories;

import util.ArrayWorker;
import entity.Order;
import enums.Status;

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

    public void cancelOrder(int id){
        orders.get(ArrayWorker.searchOrder(orders, id)).setStatus(Status.CANCELED);
    }
}
