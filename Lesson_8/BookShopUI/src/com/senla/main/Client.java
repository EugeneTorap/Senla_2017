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

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);
            input = new ObjectInputStream(inputStream);
        } catch (IOException e) {
			e.getMessage();
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

    public void sendRequest(ClientRequest request) {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
			e.getMessage();
        }
    }

    public void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}
