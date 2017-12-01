package com.senla.main;

import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.MenuController;

public class Main {
    public static void main(String[] args) {

        MenuController menuController = new MenuController();
        menuController.run();

        OnlineBookStore.getInstance().serialize();
    }
}
