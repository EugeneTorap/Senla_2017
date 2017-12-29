package com.senla.main;

import com.senla.util.ClientRequest;
import com.senla.util.ServerResponse;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Closeable {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Client(final String host, final int port) {
        try {
            socket = new Socket(host, port);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);
            input = new ObjectInputStream(inputStream);
        } catch (IOException ignored) {
        }
    }

    public ServerResponse getResponse() {
        ServerResponse response;
        try {
            response = (ServerResponse) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            response = null;
        }
        return response;
    }

    public void sendRequest(final ClientRequest request) {
        try {
            output.writeObject(request);
            output.flush();
        } catch (final IOException ignored) {
        }
    }

    public void close() throws IOException {
        if (input != null) {
            input.close();
        }
        if (output != null) {
            output.close();
        }
        if (socket != null) {
            socket.close();
        }
    }
}
