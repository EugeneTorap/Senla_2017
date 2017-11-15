package com.senla.ui;

import java.util.List;

public class Menu {
    private String name;
    private List<MenuItem> menuItems;


    public Menu(String name, List<MenuItem> menuItems) {
        this.name = name;
        this.menuItems = menuItems;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
