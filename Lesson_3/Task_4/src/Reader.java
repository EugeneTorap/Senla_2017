public class Reader {

    private String name;
    private Book[] books;

    public Reader(String name, int amount) {
        this.name = name;
        this.books = new Book[amount];
    }

    public Book[] getBooks(){
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }
}
