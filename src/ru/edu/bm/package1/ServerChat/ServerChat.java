package ru.edu.bm.package1.ServerChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ServerChat {
    private ServerSocket server;
    private HashMap<String, Client> socketHashMap;

    public ServerChat(int port) throws IOException {
        socketHashMap = new HashMap<>();
        server = new ServerSocket(port);
        new Thread(() -> {
            System.out.println("Server is work");
            while (true) {
                ServerChat.this.handle();
            }
        }).start();
    }

    void addClient(Client client, String nick) {
        if (socketHashMap.containsKey(nick)) {
            client.badNickname();
            System.out.println(client.getSocket() + " badNickname");
        } else {
            socketHashMap.put(nick, client);
            client.start();
            System.out.println(client.getSocket() + " started");
        }
    }

    void send(String message) {
        Collection<Client> clients = socketHashMap.values();
        for (Client client :
                clients) {
            client.send(message);
        }
    }

    private void handle() {
        try {
            Socket socket = server.accept();
            new Client(this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
