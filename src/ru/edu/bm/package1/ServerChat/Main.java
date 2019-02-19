package ru.edu.bm.package1.ServerChat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new ServerChat(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
