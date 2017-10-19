public class Menu {

    public static void showAllReader(Library library){
        System.out.println("\nList of all readers: \n");
        for (Reader i: library.getReaders()) {
            System.out.println(i.getName());
        }
    }

    public static void showAllBooks(Library library){
        System.out.println("\nList of all books: \n");
        for (Book i: library.getBooks()) {
            System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle() + ", " + "in library");
        }
        for (Reader j: library.getReaders()) {
            for (Book i: j.getBooks()) {
                System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle() + ", " + "in Reader");
            }
        }

    }

    public static void showAllBooksOfReader(Reader reader){
        System.out.println("\nList of all books of Reader: \n");
        for (Book i: reader.getBooks()) {
            System.out.println("Id: " + i.getId() + ", Book: " + i.getTitle());
        }
    }

    public static void showReader(Library library, int Id){
        for (Reader i : library.getReaders()) {
            if (ManagerOfLibrary.bookSearch(i.getBooks(), Id) != null) {
                System.out.println("\n" + i.getName() + " has that book \n");
                return;
            }
        }
        System.out.println("There's no reader with such book");
    }
}
