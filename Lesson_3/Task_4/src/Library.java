public class Library {
    private int amountOfBook;
    private int amountOfReader;
    private Book[] books;
    private Reader[] readers;

    public Library(Book[] books, Reader[] readers) {
        this.books = books;
        this.readers = readers;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Reader[] getReaders() {
        return readers;
    }

    public void setReaders(Reader[] readers) {
        this.readers = readers;
    }

    public void addBook(Book newBook){
        Book[] newBooks = new Book[amountOfBook + 1];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        newBooks[books.length] = newBook;
        books = newBooks;
    }

    public void addReader(Reader newReader){
        Reader[] newReaders = new Reader[amountOfReader + 1];
        System.arraycopy(readers, 0, newReaders, 0, readers.length);
        newReaders[books.length] = newReader;
        readers = newReaders;
    }

    public void showReader(int Id){
        Boolean isThereReader = false;
        for (Reader i : readers) {
            if(i.searchBook(Id)){
                isThereReader = true;
                System.out.println(i.getName());
            }
        }
        if(!isThereReader){
            System.out.println("There's no reader with such book");
        }
    }

    public void showAllBooks(){
        for (Book i: books) {
            System.out.println(i.getTitle() + ", " + i.getId() + " in library");
        }
        for (Reader i: readers) {
            i.showAllBooksOfReader();
        }
    }
}
