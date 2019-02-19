package ru.edu.bm.package1.ServerChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class Client {
    private ServerChat serverChat;
    private Socket socket;
    private Thread thread;
    private String name;

    Client(ServerChat serverChat, Socket socket) {
        this.serverChat = serverChat;
        this.socket = socket;
        thread = new Thread(() -> {
            handle();
        });
        String[] strings = read(socket).split(" ");
        if (strings.length >= 3 && strings[0].equals("nickname") && strings[1].equals("is")) {
            name = "";
            for (int i = 2; i < strings.length; i++) {
                name += strings[i];
            }
            serverChat.addClient(this, name);
        } else {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        serverChat.send("Welcome " + name + "!");
        thread.start();
    }

    public void badNickname() {
        write(socket, "badNickname\n");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        write(socket, "message " + message + "\n");
    }

    public Socket getSocket() {
        return socket;
    }

    private void handle() {
        while (true) {
            String query = read(socket);
            if (!query.equals(null)) {
                String[] querying = query.split("\n");
                for (String string : querying) {
                     methodHandle(string);
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void methodHandle(String query) {
        String[] strings = query.split(" ");
        switch (strings[0]) {
            case "message":
                String message = name + ":";
                //TODO: add time.
                for (int i = 1; i < strings.length; i++) {
                    message += " " + strings[i];
                }
                message += "\n";
                serverChat.send(message);
                break;
        }
    }

    private void write(Socket socket, String query) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(query.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String read(Socket socket) {
        try {
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[32 * 1024];
            int readBytes = in.read(buf);
            return new String(buf, 0, readBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
