package util.coparator.requestsorter;

import entity.Request;

import java.util.Comparator;

public class SortingRequestByAlphabet implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Request r1 = (Request)o1;
        Request r2 = (Request)o2;

        if (r1 != null && r2 != null){
            return r1.getBook().getTitle().compareTo(r2.getBook().getTitle());
        }

        if (r1 == null && r2 != null){
            return 1;
        } else if (r1 != null){
            return -1;
        } else {
            return 0;
        }
    }
}
