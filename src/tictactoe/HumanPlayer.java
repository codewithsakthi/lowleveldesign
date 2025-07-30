package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int[] getMove(Board board) {
        int row, col;
        while (true) {
            System.out.println(name + "'s turn (" + symbol + "). Enter row and column (e.g., 0 0): ");
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
                if (board.isValidMove(row, col)) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Invalid move. That cell is either taken or out of bounds. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers for row and column.");
                scanner.next();
            }
        }
    }
}