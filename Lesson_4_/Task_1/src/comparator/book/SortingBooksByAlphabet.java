package comparator.book;

import java.util.Comparator;
import entity.Book;

public class SortingBooksByAlphabet implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Book b1 = (Book)o1;
        Book b2 = (Book)o2;

        if (b1 != null && b2 != null){
            return b1.getTitle().compareTo(b2.getTitle());
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
