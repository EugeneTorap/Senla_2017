package util.sorter.executedordersorter;

import entity.Order;

import java.util.Comparator;

public class SortingExecutedOrdersByPrice implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Order ord1 = (Order)o1;
        Order ord2 = (Order)o2;

        if (ord1.getPrice() > ord2.getPrice()){
            return 1;
        } else if (ord1.getPrice() > ord2.getPrice()){
            return -1;
        } else {
            return 0;
        }
    }
}
