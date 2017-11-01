package util.coparator.book;

import entity.Book;

import java.util.Comparator;

public class SortingBooksByDate implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Book b1 = (Book)o1;
        Book b2 = (Book)o2;

        if (b1 != null && b2 != null){
            return b1.getDatePublished().compareTo(b2.getDatePublished());
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
