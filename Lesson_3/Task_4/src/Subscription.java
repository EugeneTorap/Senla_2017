public interface Subscription {

    void subscribeBook(Reader reader, Book newBook);
    void unSubscribeBook(Reader reader, int Id);
}