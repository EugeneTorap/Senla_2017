package com.senla.main;

import com.senla.ui.MenuController;

import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            Client.initClentHandler(socket);
            MenuController menuController = new MenuController();
            menuController.run();
            socket.close();
        } catch (Exception e) {
            Printer.print(e.getMessage());
        }
    }
}
