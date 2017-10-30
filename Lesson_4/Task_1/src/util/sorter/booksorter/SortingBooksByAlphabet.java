package util.sorter.booksorter;

import java.util.Comparator;
import entity.Book;

public class SortingBooksByAlphabet implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Book b1 = (Book)o1;
        Book b2 = (Book)o2;

        return b1.getTitle().compareTo(b2.getTitle());
    }
}
