package ru.edu.bm.package1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.err.println("Have you a trouble? Double trouble may be?");
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
            byte [] buf = new byte [32 * 1024];
            int readBytes = in.read(buf);
            return new String(buf, 0, readBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
