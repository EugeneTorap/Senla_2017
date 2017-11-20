package com.senla.main;

import com.senla.facade.OnlineBookStore;
import com.senla.ui.MenuController;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        //OnlineBookStore store = new CommonFiller().fillData();
        //OnlineBookStore store = new FileFiller().fillData();zz

        MenuController menuController = new MenuController();
        menuController.run();

        OnlineBookStore.getInstance().saveAllData();
    }
}
