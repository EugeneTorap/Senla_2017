package com.senla.main;

import static com.senla.util.Printer.print;
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
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(s.getInputStream());
    }

    public void run() {
        Object request;
        while (true) {
            try {
                while ((request = in.readObject()) != null) {
                    out.writeObject(MethodInvoker.getResponse(((Map<String, List<Object>>) request)));
                    out.flush();
                }
            } catch (Exception e) {
                print(e.getMessage());
                break;
            }
        }
    }
}
