package com.me.web;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Logger LOG = Logger.getLogger(Server.class);

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            LOG.info("Server started!");
            while (true)
                new ClientHandler(serverSocket.accept()).start();

        } catch (IOException e) {
            LOG.debug(e.getMessage(), e);
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            //e.printStackTrace();
            LOG.debug(e.getMessage(), e);
        }

    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
                //Matrix input;
                String inputLine;
                while ((inputLine = in.readObject().toString()) != null) {
                    if (".".equals(inputLine)) {
                        out.writeObject("bye");
                        break;
                    }
                    out.writeObject(inputLine);
                }

                //out.writeObject(processMatrix((Matrix)in.readObject()));

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException|ClassNotFoundException  e) {
                LOG.debug(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(5555);
    }
}
