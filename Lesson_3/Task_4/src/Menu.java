public class Menu {

    public static void showAllReader(Library library){
        Reader[] readers = library.getReaders();
        System.out.println("List of all readers: \n");
        for (Reader i: readers) {
            System.out.println(i.getName());
        }
    }

    public static void showAllBooks(Library library){
        Book[] libraryBooks = library.getBooks();
        Reader[] readers = library.getReaders();
        Book[] readersBooks;
        System.out.println("\nList of all books: \n");
        for (Book i: libraryBooks) {
            System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle() + ", " + "in library");
        }
        for (Reader j: readers) {
            readersBooks = j.getBooks();
            for (Book i: readersBooks) {
                System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle() + ", " + "in Reader");
            }
        }

    }

    public static void showAllBooksOfReader(Reader reader){
        Book[] books = reader.getBooks();
        for (Book i: books) {
            System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle());
        }
    }

    public static void showReaders(Library library, int Id){
        Reader[] readers = library.getReaders();
        Boolean isThereReader = false;
        for (Reader i : readers) {
            Book[] books = i.getBooks();
            for (Book j: books) {
                if (j.getId() == Id){
                    isThereReader = true;
                    System.out.println(i.getName());
                }
            }
        }
        if(!isThereReader){
            System.out.println("There's no reader with such book");
        }
    }


}
