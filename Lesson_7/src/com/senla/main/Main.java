package com.senla.main;

import com.senla.view.facade.OnlineBookStore;
import com.senla.ui.MenuController;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    public static void main(String[] args) {
        PropertyConfigurator.configure("resources/log4j.properties");

        MenuController menuController = new MenuController();
        menuController.run();

        OnlineBookStore.getInstance().serialize();
    }
}
