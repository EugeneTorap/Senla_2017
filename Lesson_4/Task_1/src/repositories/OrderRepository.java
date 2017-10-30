package repositories;

import util.Checker;
import entity.Order;
import enums.Status;

public class OrderRepository {
    private Order[] orders = new Order[10];


    public Order[] getOrders() {
        return orders;
    }

    public Order[] getExecutedOrders(){
        Order[] executedOrders = new Order[10];

        for (Order order : orders) {
            if (Checker.getPosition(executedOrders) != -1 && order != null && order.isExecuted()) {
                int position = Checker.getPosition(executedOrders);
                executedOrders[position] = order;
            }
        }
        return executedOrders;
    }

    public void addOrder(Order order){
        if (Checker.getPosition(orders) != -1) {
            int position = Checker.getPosition(orders);
            orders [position] = order;
            return;
        }
        System.out.println("Order repository is full");
    }

    public void canceledOrder(Order order){
        order.setStatus(Status.CANCELED);
    }

    public void delOrder(Order order){
        int index = Checker.search(orders, order.getId());

        if (index != -1){
            orders[index] = null;
        }
    }
}
