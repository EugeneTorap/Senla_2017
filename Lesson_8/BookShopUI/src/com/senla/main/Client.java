package com.senla.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Client {
    static private ObjectInputStream in;
    static private ObjectOutputStream out;


    static public void initClentHandler(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    static public Map<String, Object> send(Map<String, List<Object>> request) {
        try {
            out.writeObject(request);
            out.flush();
            return (Map<String, Object>) in.readObject();
        } catch (Exception e) {
            Printer.print(e.getMessage());
        }
        return null;
    }

    public void close() throws IOException {
        in.close();
        out.close();
    }
}
