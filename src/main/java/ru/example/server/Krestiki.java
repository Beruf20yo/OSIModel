package ru.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Krestiki {
    public final int SIZE = 3;
    public final char EMPTY = '-';
    public final char CROSS = 'X';
    public final char ZERO = '0';


    private final PrintWriter out;
    private final BufferedReader in;

    public Krestiki(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    public String main() {
        char[][] field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }

        boolean isCrossTurn = true;
        while (true) {
            out.println("Ходят " + (isCrossTurn ? "крестики" : "нолики") + " !");
            printField(field);
            String input;
            try {
                input = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] parts = input.split(" ");
            int x = Integer.parseInt(parts[0]) - 1;
            int y = Integer.parseInt(parts[1]) - 1;

            if (field[x][y] != EMPTY) {
                continue;
            }
            field[x][y] = isCrossTurn ? CROSS : ZERO;
            if (isWin(field, isCrossTurn ? CROSS : ZERO)) {
                return "Победили " + (isCrossTurn ? "крестики" : "нолики") + " !";
            } else {
                isCrossTurn = !isCrossTurn;
            }
        }
    }

    // Только для 3х3
    public boolean isWin(char[][] field, char player) {
        if (field[0][0] == player && field[0][1] == player && field[0][2] == player)
            return true;
        if (field[1][0] == player && field[1][1] == player && field[1][2] == player)
            return true;
        if (field[2][0] == player && field[2][1] == player && field[2][2] == player)
            return true;

        if (field[0][0] == player && field[1][0] == player && field[2][0] == player)
            return true;
        if (field[0][1] == player && field[1][1] == player && field[1][2] == player)
            return true;
        if (field[0][2] == player && field[1][2] == player && field[2][2] == player)
            return true;

        if (field[0][0] == player && field[1][1] == player && field[2][2] == player)
            return true;
        return field[2][0] == player && field[1][1] == player && field[0][2] == player;
    }

    public void printField(char[][] field) {
        for (char[] row : field) {
            for (char cell : row) {
                out.print(cell + " ");
            }
            out.println();
        }
    }
}
