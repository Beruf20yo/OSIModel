package ru.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8089;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            Scanner consoleReader = new Scanner(System.in);
            while (true) {
                //TODO Вывод пока есть строка
                String line;
                StringBuilder s = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    s.append(line);
                }
                System.out.println(s);
                String answer = consoleReader.nextLine();
                if (answer.equals("exit")) {
                    break;
                }
                out.println(answer);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
