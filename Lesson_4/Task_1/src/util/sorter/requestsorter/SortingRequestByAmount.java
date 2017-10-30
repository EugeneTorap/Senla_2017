package util.sorter.requestsorter;

import entity.Request;

import java.util.Comparator;

public class SortingRequestByAmount implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Request r1 = (Request)o1;
        Request r2 = (Request)o2;

        if (r1.getAmount() > r2.getAmount()){
            return 1;
        } else if (r1.getAmount() > r2.getAmount()){
            return -1;
        } else {
            return 0;
        }
    }
}
