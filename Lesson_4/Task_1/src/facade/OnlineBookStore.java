package facade;

import beans.BookManager;
import beans.OrderManager;
import beans.RequestManager;
import entity.Book;
import entity.Order;
import entity.Reader;
import entity.Request;
import util.ArrayWorker;
import util.Checker;
import util.FileWorker;
import util.Printer;
import enums.SortingType;

import java.text.ParseException;

public class OnlineBookStore {

    private BookManager bookManager;
    private OrderManager orderManager;
    private RequestManager requestManager;
    private FileWorker fileWorker = new FileWorker("book.txt", "reader.txt",
            "order.txt", "request.txt");

    public OnlineBookStore(BookManager bookManager, OrderManager orderManager, RequestManager requestManager) {
        this.bookManager = bookManager;
        this.orderManager = orderManager;
        this.requestManager = requestManager;
    }


    public void showBooksSortedBy(SortingType type) {
        switch (type) {
            case ALPHABET:
                Printer.printArray(bookManager.sortBooksByAlphabet());
                break;
            case DATE:
                Printer.printArray(bookManager.sortBooksByDate());
                break;
            case PRICE:
                Printer.printArray(bookManager.sortBooksByPrice());
                break;
            case IS_STORE:
                Printer.printArray(bookManager.sortBooksByStore());
                break;
        }
    }

    public void showUnsoldBooksSortedBy(SortingType type) {
        switch (type) {
            case DATE:
                Printer.printArray(bookManager.sortUnsoldBooksByDate());
                break;
            case PRICE:
                Printer.printArray(bookManager.sortUnsoldBooksByPrice());
                break;
        }
    }

    public void showOrdersSortedBy(SortingType type) {
        switch (type) {
            case DATE:
                Printer.printArray(orderManager.sortOrdersByDate());
                break;
            case PRICE:
                Printer.printArray(orderManager.sortOrdersByPrice());
                break;
            case STATUS:
                Printer.printArray(orderManager.sortOrdersByStatus());
                break;
        }
    }

    public void showExecutedOrdersSortedBy(SortingType type) {
        switch (type) {
            case PRICE:
                Printer.printArray(orderManager.sortExecutedOrdersByPrice());
                break;
            case DATE:
                Printer.printArray(orderManager.sortExecutedOrdersByDate());
                break;
        }
    }

    public void showRequestsSortedBy(SortingType type) {
        requestManager.setRequestAmount();
        switch (type) {
            case AMOUNT:
                showBookRequests(bookManager.sortBooksByAmount());
                break;
            case ALPHABET:
                showBookRequests(bookManager.sortBooksByAlphabet());
                break;
        }
    }

    private void showBookRequests(Book[] books){
        System.out.println();
        for (Book book : books) {
            if (book != null) {
                System.out.println(book.getTitle() + " " + book.getRequestAmount());
                if (requestManager.requestForBook(book) != null){
                    for (Request request : requestManager.requestForBook(book)) {
                        System.out.println(request.getId() + " " + request.getReader().getName());
                    }
                }
                System.out.println("-----------------------");
            }
        }
    }

    public void showAllPrice(){
        Printer.print(orderManager.getAllPrice());
    }

    public void showAmountExecutedOrders(){
        Printer.print(orderManager.getAmountExecutedOrders());
    }

    public void showOrderInfo(int id) {
        Printer.print(ArrayWorker.search(orderManager.getOrderRepository().getOrders(), id));
    }

    public void showBookInfo(int id) {
        Printer.print(ArrayWorker.search(bookManager.getBookRepository().getBooks(), id));
    }

    public void addReader(Reader newReader){
        requestManager.getRequestRepository().addReader(newReader);
    }

    public void addBook(Book newBook){
        bookManager.getBookRepository().addBook(newBook);
    }

    public void addBookOnStore(Book book) {
        bookManager.getBookRepository().addBookOnStore(book.getId());
    }

    public void delBookFromStore(Book book) {
        bookManager.getBookRepository().delBookFromStore(book.getId());
    }

    public void addOrder(Order order) {
        orderManager.getOrderRepository().addOrder(order);
    }

    public void cancelOrder(Order order) {
        orderManager.getOrderRepository().canceledOrder(order);
    }

    public void addRequest(Request request){
        requestManager.getRequestRepository().addRequest(request);
    }

    public void saveAllData(){
        fileWorker.saveToFile(bookManager.getBookRepository().getBooks());
        fileWorker.saveToFile(orderManager.getOrderRepository().getOrders());
        fileWorker.saveToFile(requestManager.getRequestRepository().getReaders());
        fileWorker.saveToFile(requestManager.getRequestRepository().getRequests());
    }

    public void loadAllData() throws ParseException {
        bookManager.getBookRepository().setBooks(fileWorker.loadBooks());
        requestManager.getRequestRepository().setReaders(fileWorker.loadReader());
        orderManager.getOrderRepository().setOrders(fileWorker.loadOrders(bookManager.getBookRepository().getBooks()));
        requestManager.getRequestRepository().setRequests(fileWorker.loadRequests(bookManager.getBookRepository().getBooks(),
                requestManager.getRequestRepository().getReaders()));
    }
}
