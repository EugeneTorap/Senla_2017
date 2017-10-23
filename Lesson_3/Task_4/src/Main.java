public class Main {

    public static void main(String[] args) {

        Book Crime = new Book("Crime", 1);
        Book History = new Book("History", 2);
        Book War = new Book("War", 3);

        Reader Jony = new Reader("Jony", 1);
        Reader Bob = new Reader("Bob", 2);

        ManagerOfLibrary manager = new ManagerOfLibrary(3, 2);

        manager.addBookInLibrary(Crime);
        manager.addBookInLibrary(History);
        manager.addBookInLibrary(War);

        manager.addReader(Jony);
        manager.addReader(Bob);

        manager.addBookToReader(Crime, Bob);
        manager.addBookToReader(History, Jony);

        //manager.addBook(new Book("Love", 9));
        //manager.addReader(new Reader("Jackson", 1));
        manager.subscribeBook(Bob, 3);
        manager.unSubscribeBook(Bob, 1);

        manager.showAllBooks();
        //manager.showAllBooksOfReader(Bob);
        //manager.showAllReader();
        //manager.showReader(5);
    }
}
