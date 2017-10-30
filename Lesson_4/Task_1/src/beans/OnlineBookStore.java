package beans;

import entity.Book;
import entity.Order;
import entity.Request;
import util.Checker;
import util.Printer;
import enums.SortingType;

public class OnlineBookStore {

    private BookManager bookManager;
    private OrderManager orderManager;
    private RequestManager requestManager;
    private SortingType sortingType;

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
            case ISSTORE:
                Printer.printArray(bookManager.sortBooksByStore());
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
        switch (type) {
            case AMOUNT:
                Printer.printArray(requestManager.sortRequestsByAmount());
                break;
            case ALPHABET:
                Printer.printArray(requestManager.sortRequestsByAlphabet());
                break;
        }
    }

    public void showAllPrice(){
        Printer.print(orderManager.getAllPrice());
    }

    public void showAmountExecutedOrders(){
        Printer.print(orderManager.getAmountExecutedOrders());
    }

    public void showOrderInfo(int id) {
        Printer.print(Checker.search(orderManager.getOrderRepository().getOrders(), id));
    }

    public void showBookInfo(int id) {
        Book book = Checker.search(bookManager.getBookRepository().getBooks(), id);
        Printer.print(book);
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
}
