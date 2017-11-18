package com.senla.comparator.book;

import com.senla.entity.Book;

import java.util.Comparator;

public class SortingBooksByDateRec implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Book b1 = (Book)o1;
        Book b2 = (Book)o2;

        if (b1 != null && b2 != null){
            return b1.getDateReceipted().compareTo(b2.getDateReceipted());
        }

        if (b1 == null && b2 != null){
            return 1;
        } else if (b1 != null){
            return -1;
        } else {
            return 0;
        }
    }
}
