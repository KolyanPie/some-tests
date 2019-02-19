package ru.edu.bm.package1;

import java.util.Scanner;

public class Cmd {
    private Thread thread;
    private Scanner sc;

    public Cmd() {
        sc = new Scanner(System.in);
        thread = new Thread(() -> run());
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.stop();
    }

    private void run() {
        String query = null;
        while (!query.equals("/exit")) {
            handle(query);
            query = sc.nextLine();
        }
    }

    private void handle(String query) {
        if (query.equals(null)) {
            return;
        }
        String[] strings = query.split(" ");
        switch (strings[0]) {

        }
    }


}
