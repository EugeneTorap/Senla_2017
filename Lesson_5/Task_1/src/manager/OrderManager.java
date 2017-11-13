package manager;

import repositories.BookRepository;
import repositories.OrderRepository;
import entity.Order;
import util.ArrayWorker;
import util.FileWorker;
import util.Printer;

import java.text.ParseException;
import java.util.Comparator;

public class OrderManager {
    private OrderRepository orderRepository = new OrderRepository();
    private BookRepository bookRepository;
    private FileWorker fileWorker = new FileWorker("book.txt", "reader.txt",
            "order.txt", "request.txt");

    public OrderManager(BookManager bookManager) {
        this.bookRepository = bookManager.getBookRepository();
    }

    public void saveToFile(){
        fileWorker.saveOrders(orderRepository.getOrders());
    }

    public void loadFromFile() throws ParseException {
        orderRepository.setOrders(fileWorker.loadOrders(bookRepository.getBooks()));
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void cancelOrder(int id) {
        orderRepository.cancelOrder(id);
    }

    public void showOrderInfo(int id) {
        Printer.print(ArrayWorker.searchOrder(orderRepository.getOrders(), id));
    }

    public int getAllPrice(){
        int sum = 0;
        for (Order order: orderRepository.getExecutedOrders()) {
            sum += order.getPrice();
        }
        return sum;
    }
    
    public int getAmountExecutedOrders(){
        return orderRepository.getExecutedOrders().size();
    }

    public void showOrders(){
        Printer.printOrders(orderRepository.getOrders());
    }

    public void showExecutedOrders(){
        Printer.printOrders(orderRepository.getExecutedOrders());
    }

    public void sortOrders(Comparator comparator){
        orderRepository.getOrders().sort(comparator);
    }

    public void sortExecutedOrders(Comparator comparator){
        orderRepository.getExecutedOrders().sort(comparator);
    }
}
