package util.sorter.executedordersorter;

import entity.Order;

import java.util.Comparator;

public class SortingExecutedOrdersByDate implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Order ord1 = (Order)o1;
        Order ord2 = (Order)o2;

        return ord1.getDateExecuted().compareTo(ord2.getDateExecuted());
    }
}
