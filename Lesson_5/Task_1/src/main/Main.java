package main;

import enums.SortingType;
import facade.OnlineBookStore;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);

        OnlineBookStore store = new CommonFiller().fillData();
        //OnlineBookStore store = new FileFiller().fillData();

        Boolean exist = false;
        while (!exist) {
            System.out.println(
                            "What do you want to do?\n\n" +
                            "1 --- Show list of books\n" +
                            "2 --- Show list of orders\n" +
                            "3 --- Show list of book requests\n" +
                            "4 --- Show list of executed orders\n" +
                            "5 --- Show list of unsold books\n" +
                            "6 --- Show all price\n" +
                            "7 --- Show amount executed orders\n" +
                            "8 --- Show information of the order\n" +
                            "9 --- Show information of the book\n" +
                            "10 --- Add book on store\n" +
                            "11 --- Delete book from store\n" +
                            "12 --- Add the order\n" +
                            "13 --- Cancel the order\n" +
                            "14 --- Add request\n" +
                            "0 --- Exit\n\n"
            );
            String num = in.next();
            switch (num){
                case "1":
                    System.out.println(
                            "1 --- Sort by alphabet\n" +
                            "2 --- Sort by date\n" +
                            "3 --- Sort by price\n" +
                            "4 --- Sort by stored\n"
                    );
                    String num1 = in.next();
                    switch (num1){
                        case "1":
                            store.showBooksSortedBy(SortingType.ALPHABET); break;
                        case "2":
                            store.showBooksSortedBy(SortingType.DATE); break;
                        case "3":
                            store.showBooksSortedBy(SortingType.PRICE); break;
                        case "4":
                            store.showBooksSortedBy(SortingType.IS_STORE); break;
                    }
                    break;
                case "2":
                    System.out.println(
                                    "1 --- Sort by date\n" +
                                    "2 --- Sort by price\n" +
                                    "3 --- Sort by status\n"
                    );
                    String num2 = in.next();
                    switch (num2){
                        case "1":
                            store.showOrdersSortedBy(SortingType.DATE); break;
                        case "2":
                            store.showOrdersSortedBy(SortingType.PRICE); break;
                        case "3":
                            store.showOrdersSortedBy(SortingType.STATUS); break;
                    }
                    break;
                case "3":
                    System.out.println(
                                    "1 --- Sort by alphabet\n" +
                                    "2 --- Sort by amount\n"
                    );
                    String num3 = in.next();
                    switch (num3){
                        case "1":
                            store.showRequestsSortedBy(SortingType.ALPHABET); break;
                        case "2":
                            store.showRequestsSortedBy(SortingType.AMOUNT); break;
                    }
                    break;
                case "4":
                    System.out.println(
                                    "1 --- Sort by date\n" +
                                    "2 --- Sort by price\n"
                    );
                    String num4 = in.next();
                    switch (num4){
                        case "1":
                            store.showExecutedOrdersSortedBy(SortingType.DATE); break;
                        case "2":
                            store.showExecutedOrdersSortedBy(SortingType.PRICE); break;
                    }
                    break;
                case "5":
                    System.out.println(
                                    "1 --- Sort by date\n" +
                                    "2 --- Sort by price\n"
                    );
                    String num5 = in.next();
                    switch (num5){
                        case "1":
                            store.showUnsoldBooksSortedBy(SortingType.DATE); break;
                        case "2":
                            store.showUnsoldBooksSortedBy(SortingType.PRICE); break;
                    }
                    break;
                case "6":
                    store.showAllPrice(); break;
                case "7":
                    store.showAmountExecutedOrders(); break;
                case "8":
                    System.out.println("Input order ID: ");
                    store.showOrderInfo(in.nextInt()); break;
                case "9":
                    System.out.println("Input book ID: ");
                    store.showBookInfo(in.nextInt()); break;
                case "10":
                    System.out.println("Input book ID: ");
                    store.addBookOnStore(in.nextInt()); break;
                case "11":
                    System.out.println("Input book ID: ");
                    store.delBookFromStore(in.nextInt()); break;
                case "12":
                    System.out.println("Input order ID: ");
                    //store.addOrder(in.nextInt()); break;
                case "13":
                    System.out.println("Input order ID: ");
                    store.cancelOrder(in.nextInt()); break;
                case "14":
                    System.out.println("Input request ID: ");
                    //store.addRequest(in.nextInt()); break;
                case "0":
                    exist = true;

            }
        }
        store.saveAllData();
    }
}
