package ru.edu.bm.package1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Chat {
    private Socket socket;
    private Thread thread;

    public Chat(String host, int port, String name) throws IOException {
        socket = new Socket(host, port);
        write(socket, "nickname is " + name);
        thread = new Thread(() -> handle());
        thread.start();
    }

    public void send(String message) {
        write(socket, "message " + message + "\n");
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
                String message = strings[1];
                for (int i = 2; i < strings.length; i++) {
                    message += " " + strings[i];
                }
                message += "\n";
                System.out.print(message);
                break;
            case "badNickname":
                System.err.println("Потом пофикшу, перезапускай.");
                System.exit(8888);
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
