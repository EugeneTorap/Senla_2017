package com.senla.ui;

public class Navigator {
    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        super();
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        for(int i = 0; i<currentMenu.getMenuItems().size(); i++) {
            if (currentMenu.getMenuItems().get(i).getTitle().equals("exit")){
                System.out.println(0 + " -- " + currentMenu.getMenuItems().get(i));
                continue;
            }
            System.out.println((i + 1) + " -- " + currentMenu.getMenuItems().get(i));
        }
    }

    public void navigate(Integer index) {
        if (index >= 0 && index < currentMenu.getMenuItems().size()) {
            MenuItem menuItem = currentMenu.getMenuItems().get(index);
            if (menuItem != null) {
                if (menuItem.getAction() != null)
                    menuItem.doAction();
                else {
                    currentMenu = menuItem.getNextMenu();
                }
            }
        return;
        }
        System.out.println("There's no such item");
    }
}
