public interface Subscription {

    void subscribeBook(Library library, Reader reader, int Id);
    void unSubscribeBook(Library library, Reader reader, int Id);
}