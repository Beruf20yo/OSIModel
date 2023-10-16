package ru.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8089;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner consoleReader = new Scanner(System.in)) {
            while (!clientSocket.isClosed()) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    String answer = consoleReader.nextLine();
                    if (answer.equals("exit")) {
                        break;
                    }
                    out.println(answer);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
