package com.senla.main;

import com.senla.ui.MenuController;
import static com.senla.util.Printer.print;

import java.net.Socket;

public class Main {
    private static final int PORT = 1235;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT);
            new Client(socket);
            MenuController menuController = new MenuController();
            menuController.run();
            socket.close();
            Client.close();
        } catch (Exception e) {
            print(e.getMessage());
        }
    }
}
