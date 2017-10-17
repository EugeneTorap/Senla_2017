public class Reader implements Subscription {
    private String name;
    private int amount;
    private Book[] books;

    public Reader(String name, int amount) {
        this.name = name;
        this.books = new Book[amount];
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void subscribeBook(Book newBook) {
        Book[] newBooks = new Book[amount + 1];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        newBooks[books.length] = newBook;
        books = newBooks;
    }

    @Override
    public void unSubscribeBook(int Id) {
        Book[] newBooks = new Book[amount - 1];
        int count = 0;

        Boolean isThereBook = true;
        for (int i = 0; i < amount; i++) {
            if (Id == books[i].getId()) {
                isThereBook = false;
                continue;
            }
            newBooks[count] = books[i];
            count++;
        }
        if (isThereBook){
            System.out.println("There's no such book");
        }else{
            books = newBooks;
        }
    }

    public void showAllBooksOfReader(){
        for (Book i: books) {
            System.out.println(i.getTitle() + ", " + i.getId() + "in Reader");
        }
    }

    public Boolean searchBook(int Id){
        for (Book i: books) {
            if (i.getId() == Id){
                return true;
            }
        }
        return false;
    }
}
