package com.senla.main;

import com.senla.enums.SortingType;
import com.senla.facade.OnlineBookStore;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);

        //OnlineBookStore store = new CommonFiller().fillData();
        OnlineBookStore store = new FileFiller().fillData();

        store.saveAllData();
    }
}
