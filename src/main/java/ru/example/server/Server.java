package ru.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server started");
        int port = 8089;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port %d%n", clientSocket.getPort());
                    out.println("Hi, user! What's your name?");

                    final String name = in.readLine();
                    out.println(String.format("Hello %s, your port is %d. \"How old are you?\"", name, clientSocket.getPort()));

                    String age = in.readLine();
                    out.println(((Integer.parseInt(age) > 18) ? "Whats up bro?" : "How do you do fellow kids?") + "  1. Fine  2. Bad");

                    String stateOfMind = in.readLine();
                    out.println(((Integer.parseInt(stateOfMind) == 1) ? "Happy for you buddy. " : "Everything will work out, don't worry. ")
                            + "Would like to play some game with me?(yes/no)");

                    String playGame = in.readLine();
                    out.println((playGame.equalsIgnoreCase("yes")) ? "Let's play!" :
                            ("Thanks for dialog, " + name + ", you can white 'exit' to leave this dialog"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}