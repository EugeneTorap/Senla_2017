public class Main {

    public static void main(String[] args) {

        Reader Jony = new Reader("Jony", 3);
        Book[] forJony = {new Book("Crime", 1), new Book("God", 2), new Book("Cat", 3)};
        Jony.setBooks(forJony);
        Reader Vasia = new Reader("Vasia", 1);
        Book[] forVasia = {new Book("History", 4)};
        Vasia.setBooks(forVasia);
        Reader Bob = new Reader("Bob", 2);
        Book[] forBob = {new Book("Rain", 5), new Book("War", 6)};
        Bob.setBooks(forBob);

        Reader[] readers = {Jony, Vasia, Bob};
        Book[] forLibrary = {new Book("English", 7), new Book("Russia", 8)};
        Library library = new Library(2, 3);
        library.setBooks(forLibrary);
        library.setReaders(readers);

        ManagerOfLibrary manager = new ManagerOfLibrary();
        //manager.addBook(library, new Book("Love", 9));
        //manager.addReader(library, new Reader("Jackson", 1));
        //manager.subscribeBook(library, Bob, 7);
        //manager.unSubscribeBook(library, Bob, 5);

        //Menu.showAllBooks(library);
        //Menu.showAllBooksOfReader(Bob);
        //Menu.showAllReader(library);
        //Menu.showReader(library, 5);
    }
}
