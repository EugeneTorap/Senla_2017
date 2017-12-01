package comparator.order;

import entity.Order;

import java.util.Comparator;

public class SortingOrdersByStatus implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Order ord1 = (Order)o1;
        Order ord2 = (Order)o2;

        if (ord1 != null && ord2 != null){
            return ord1.getStatus().compareTo(ord2.getStatus());
        }

        if (ord1 == null && ord2 != null){
            return 1;
        } else if (ord1 != null){
            return -1;
        } else {
            return 0;
        }

    }
}
