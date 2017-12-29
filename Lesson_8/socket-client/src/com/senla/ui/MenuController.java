package com.senla.ui;

import com.senla.util.Input;

public class MenuController {
    private Builder builder;
    private Navigator navigator;


    public MenuController() {
        builder = new Builder();
        navigator = new Navigator(builder.buildMenu());
    }

    public void run() {
        while(true) {
            navigator.printMenu();
            Integer num = Input.nextInt("Input num: ");

            if(num == 0) {
                break;
            }
            navigator.navigate(num - 1);
        }
    }
}
