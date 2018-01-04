package com.senla.main;

import com.senla.util.MethodCreator;
import com.senla.util.ClientRequest;
import com.senla.util.ServerResponse;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread implements Closeable {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerThread(Socket s) throws IOException {
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
    }

    public void run() {
        while (true) {
            try {
                ClientRequest request = (ClientRequest) in.readObject();
                ServerResponse response = MethodCreator.execute(request);
                out.writeObject(response);
                out.flush();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    public void close() throws IOException {
        in.close();
        out.close();
    }
}
