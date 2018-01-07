package com.senla.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Client {
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    public Client(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public static Map<String, Object> send(Map<String, List<Object>> request) {
        try {
            out.writeObject(request);
            out.flush();
            return (Map<String, Object>) in.readObject();
        } catch (Exception e) {
            Printer.print(e.getMessage());
        }
        return null;
    }

    public static void close() throws IOException {
        in.close();
        out.close();
    }
}
