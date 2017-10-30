package beans;

import entity.Book;
import entity.Order;
import entity.Reader;
import entity.Request;
import enums.SortingType;
import enums.Status;
import repositories.BookRepository;
import repositories.OrderRepository;
import repositories.RequestRepository;

public class Main {
    public static void main(String[] args) {

        //SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");

        Reader r1 = new Reader("Eugene");
        Reader r2 = new Reader("Alla");
        Reader r3 = new Reader("Vlad");
        Reader r4 = new Reader("Ania");


        Book b1 = new Book(1, "Night", 5);
        Book b2 = new Book(2, "War", 6);
        Book b3 = new Book(3, "Rome", 2);
        Book b4 = new Book(4, "Space", 9);
        Book b5 = new Book(5, "Knight", 7);

        Book[] books = {b1, b2, b3, b4, b5};

        Order o1 = new Order(20, r1, Status.EXECUTED);
        Order o2 = new Order(30, r2, Status.EXECUTED);


        Request req1 = new Request(1, r3);
        Request req2 = new Request(2, r4);

        BookRepository bookRepository = new BookRepository();
        BookManager bookManager = new BookManager(bookRepository);
        OrderRepository orderRepository = new OrderRepository();
        OrderManager orderManager = new OrderManager(orderRepository);
        RequestRepository requestRepository = new RequestRepository();
        RequestManager requestManager = new RequestManager(requestRepository);
        OnlineBookStore store = new OnlineBookStore(bookManager, orderManager, requestManager);


        store.addBook(b1);
        store.addBook(b2);
        store.addBook(b3);
        store.addBook(b4);
        store.addBook(b5);

        store.addBookOnStore(b1);
        store.addBookOnStore(b2);

        store.showBookInfo(b1.getId());
        store.showAllPrice();
        store.showBooksSortedBy(SortingType.ALPHABET);

    }
}
