package com.senla.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Main {
    public static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Started, waiting for connection");
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
