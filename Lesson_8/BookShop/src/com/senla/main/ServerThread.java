package com.senla.main;

import com.senla.util.MethodInvoker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ServerThread extends Thread {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerThread(Socket s) throws IOException {
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
    }

    public void run() {
        Object request;
        while (true) {
            try {
                while ((request = in.readObject()) != null) {
                    out.writeObject(MethodInvoker.getResponse(((Map<String, List<Object>>) request)));
                    out.flush();
                }
                in.close();
                out.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
