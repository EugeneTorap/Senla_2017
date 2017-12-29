package com.senla.comparator.book;

import com.senla.model.entity.Book;

import java.util.Comparator;

public class SortingBooksByAmount implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Book r1 = (Book)o1;
        Book r2 = (Book)o2;

        if (r1 != null && r2 != null){
            return Integer.compare(r1.getRequestAmount(), r2.getRequestAmount());
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
