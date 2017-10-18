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
        Library library = new Library(forLibrary, readers);

        ManagerOfLibrary manager = new ManagerOfLibrary();
        //manager.addBook(library, new Book("Love", 9));
        //manager.addReader(library, new Reader("Jackson", 1));
        //manager.subscribeBook(Vasia, new Book("Cooking", 11));
        //manager.unSubscribeBook(Bob, 5);

        //Menu.showAllBooks(library);
        //Menu.showAllBooksOfReader(Bob);
        //Menu.showAllReader(library);
        //Menu.showReaders(library, 5);
    }
}
