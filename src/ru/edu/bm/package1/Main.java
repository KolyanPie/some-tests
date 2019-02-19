package ru.edu.bm.package1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.err.println("Have you a trouble? Double trouble may be?");
        System.out.println("Write your nickname! Birch!");
        Scanner sc = new Scanner(System.in);
        try {
            Chat chat = new Chat("localhost", 8888, sc.nextLine());
            while (true) {
                chat.send(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
