package main;

import enums.SortingType;
import facade.OnlineBookStore;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        //OnlineBookStore store = new CommonFiller().fillData();
        OnlineBookStore store = new FileFiller().fillData();

        //store.showBookInfo(b1.getId());
        //store.showAllPrice();
        store.showBooksSortedBy(SortingType.ALPHABET);
        //store.showUnsoldBooksSortedBy(SortingType.DATE);
        store.showOrdersSortedBy(SortingType.DATE);
        //store.showExecutedOrdersSortedBy(SortingType.PRICE);
        //store.showRequestsSortedBy(SortingType.ALPHABET);

        store.saveAllData();
    }
}
