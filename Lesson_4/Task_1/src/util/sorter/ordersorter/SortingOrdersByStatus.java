package util.sorter.ordersorter;

import entity.Order;

import java.util.Comparator;

public class SortingOrdersByStatus implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Order ord1 = (Order)o1;
        Order ord2 = (Order)o2;

        return ord1.getStatus().compareTo(ord2.getStatus());
    }
}
