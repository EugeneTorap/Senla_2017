public interface Subscription {

    void subscribeBook(Reader reader, int id);
    void unSubscribeBook(Reader reader, int id);
}