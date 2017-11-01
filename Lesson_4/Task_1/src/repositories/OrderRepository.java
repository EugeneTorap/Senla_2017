package repositories;

import util.ArrayWorker;
import util.Checker;
import entity.Order;
import enums.Status;

public class OrderRepository {
    private Order[] orders = new Order[50];


    public Order[] getOrders() {
        return orders;
    }

    public Order[] getExecutedOrders(){
        Order[] executedOrders = new Order[50];

        for (Order order : orders) {
            if (Checker.getPosition(executedOrders) != -1 && order != null && order.isExecuted()) {
                int position = Checker.getPosition(executedOrders);
                executedOrders[position] = order;
            }
        }
        return executedOrders;
    }

    public void addOrder(Order order){
        if (Checker.getPosition(orders) == -1) {
            orders = ArrayWorker.extendArray(orders);
        }
        int position = Checker.getPosition(orders);
        orders [position] = order;
    }

    public void canceledOrder(Order order){
        order.setStatus(Status.CANCELED);
    }
}
