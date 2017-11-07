package repositories;

import util.ArrayWorker;
import util.Checker;
import entity.Order;
import enums.Status;

public class OrderRepository {
    private Order[] orders = new Order[50];
    private Order[] executedOrders;

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public Order[] getExecutedOrders(){
        return executedOrders;
    }

    public void updateExecutedOrders(){
        int count = 0;
        for (Order order : orders) {
            if (order != null && order.isExecuted()) {
                count++;
            }
        }
        Order[] executedOrders = new Order[count];

        for (Order order : orders) {
            if (Checker.getPosition(executedOrders) != -1 && order != null && order.isExecuted()) {
                int position = Checker.getPosition(executedOrders);
                executedOrders[position] = order;
            }
        }
        this.executedOrders = executedOrders;
    }

    public void addOrder(int id){
        if (Checker.getPosition(orders) == -1) {
            orders = ArrayWorker.extendArray(orders);
        }
        int position = Checker.getPosition(orders);
        orders [position] = orders[ArrayWorker.search(orders, id)];
    }

    public void canceledOrder(int id){
        orders[ArrayWorker.search(orders, id)].setStatus(Status.CANCELED);
    }
}
