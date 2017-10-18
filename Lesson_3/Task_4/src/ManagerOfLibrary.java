public class ManagerOfLibrary implements Subscription {

    public void addBook(Library library, Book newBook){
        Book[] books = library.getBooks();
        Book[] newBooks = new Book[books.length + 1];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        newBooks[books.length] = newBook;
        library.setBooks(newBooks);
    }

    public void addReader(Library library, Reader newReader){
        Reader[] readers = library.getReaders();
        Reader[] newReaders = new Reader[readers.length + 1];
        System.arraycopy(readers, 0, newReaders, 0, readers.length);
        newReaders[readers.length] = newReader;
        library.setReaders(newReaders);
    }

    @Override
    public void subscribeBook(Reader reader, Book newBook) {
        Book[] books = reader.getBooks();
        Book[] newBooks = new Book[books.length + 1];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        newBooks[books.length] = newBook;
        reader.setBooks(newBooks);
    }

    @Override
    public void unSubscribeBook(Reader reader, int Id) {
        Book[] books = reader.getBooks();
        Book[] newBooks = new Book[books.length - 1];
        int count = 0;

        Boolean isThereBook = true;
        for (Book book : books) {
            if (Id == book.getId()) {
                isThereBook = false;
                continue;
            }
            if (count == books.length - 1){ break;}

            newBooks[count] = book;
            count++;
        }
        if (isThereBook){
            System.out.println("There's no such book");
        }else{
            reader.setBooks(newBooks);
        }
    }
}
