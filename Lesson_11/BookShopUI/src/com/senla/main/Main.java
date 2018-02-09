package com.senla.main;

import com.senla.ui.MenuController;
import static com.senla.util.Printer.print;

import java.net.Socket;

public class Main {
    public static final int PORT = 1234;
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT);
            new Client(socket);
            MenuController menuController = new MenuController();
            menuController.run();
            socket.close();
        } catch (Exception e) {
            print(e.getMessage());
        }
    }
}
