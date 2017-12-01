public class Checker {

    public static int getPosition(Book[] books){
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null)
                return i;
        }
        return -1;
    }

    public static int getPosition(Reader[] readers){
        for (int i = 0; i < readers.length; i++) {
            if (readers[i] == null)
                return i;
        }
        return -1;
    }
}
