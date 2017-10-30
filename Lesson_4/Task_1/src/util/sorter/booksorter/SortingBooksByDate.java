package util.sorter.booksorter;

import entity.Book;

import java.util.Comparator;

public class SortingBooksByDate implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Book b1 = (Book)o1;
        Book b2 = (Book)o2;

        return b1.getDatePublished().compareTo(b2.getDatePublished());
    }
}
