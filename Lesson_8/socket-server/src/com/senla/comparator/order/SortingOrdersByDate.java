package com.senla.comparator.order;

import com.senla.model.entity.Order;

import java.util.Comparator;

public class SortingOrdersByDate implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Order ord1 = (Order)o1;
        Order ord2 = (Order)o2;

        if (ord1 != null && ord2 != null){
            return ord1.getDateExecuted().compareTo(ord2.getDateExecuted());
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
