package com.me.web;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final Logger LOG = Logger.getLogger(Client.class);

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            LOG.debug("Error when initializing connection", e);
        }

    }

    public String sendMessage(String msg) {
        try {
            out.writeObject(msg);
            return (String)in.readObject();
        } catch (Exception e) {
            return null;
        }
    }
/*
    public Integer[] sendMatrix(Matrix m){
        try{
            out.writeObject(m);
            return (Integer[])in.readObject();
        }catch (Exception e){
            return null;
        }
    }
*/
    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            LOG.debug("Error when closing", e);
        }

    }

    public static void main(String[] args){
        Client client = new Client();
        client.startConnection("127.0.0.1", 5555);

        System.out.println(client.sendMessage("hello"));
        System.out.println(client.sendMessage("."));
        client.stopConnection();
    }
}
