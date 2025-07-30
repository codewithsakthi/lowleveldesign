package tictactoe;

import java.util.Stack;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Stack<Board> boardHistory; // For undo/redo
    private Stack<Board> redoHistory; // For redo
    private long startTime;
    private long endTime;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        this.currentPlayer = player1;
        this.boardHistory = new Stack<>();
        this.redoHistory = new Stack<>();
        saveBoardState();
    }

    private void saveBoardState() {
        boardHistory.push(new Board(board)); // Push a copy of the current board
        redoHistory.clear(); // Clear redo history on new move
    }

    // Modified to return GameResult
    public GameResult play() {
        Scanner scanner = new Scanner(System.in);
        startTime = System.currentTimeMillis();
        System.out.println("\n--- Game Started! ---");

        String winnerName = null;
        boolean isDraw = false;
        long duration; // Declare duration here

        while (true) {
            board.printBoard();
            int[] move;

            // Handle undo/redo options
            if (!(currentPlayer instanceof Bot)) { // Only offer undo/redo to human players
                System.out.println("Enter your move (row col) or 'u' for undo, 'r' for redo, 'q' to quit:");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Game quit by player.");
                    endTime = System.currentTimeMillis();
                    duration = endTime - startTime; // Calculate duration
                    System.out.println("Game duration: " + formatDuration(duration)); // Print duration immediately
                    return new GameResult(player1.getName(), player2.getName(), null, false, duration); // Game quit, no winner
                } else if (input.equalsIgnoreCase("u")) {
                    undoMove();
                    continue; // Skip current player's turn, re-prompt
                } else if (input.equalsIgnoreCase("r")) {
                    redoMove();
                    continue; // Skip current player's turn, re-prompt
                } else {
                    try {
                        String[] parts = input.split(" ");
                        move = new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid input format. Please enter row and column or 'u'/'r'/'q'.");
                        continue; // Re-prompt
                    }
                }
            } else { // Bot's turn, automatically get move
                move = currentPlayer.getMove(board);
            }

            if (board.isValidMove(move[0], move[1])) {
                board.makeMove(move[0], move[1], currentPlayer.getSymbol());
                saveBoardState(); // Save state after a valid move
            } else {
                if (!(currentPlayer instanceof Bot)) { // Only tell human player about invalid move
                    System.out.println("Invalid move. Try again.");
                }
                continue; // Stay on the same player's turn
            }

            char winner = board.checkWin();
            if (winner != Board.EMPTY) {
                board.printBoard();
                winnerName = currentPlayer.getName();
                System.out.println("\n--- " + winnerName + " (" + winner + ") wins! ---");
                endTime = System.currentTimeMillis();
                duration = endTime - startTime; // Calculate duration
                System.out.println("Game duration: " + formatDuration(duration)); // Print duration immediately
                return new GameResult(player1.getName(), player2.getName(), winnerName, false, duration); // Game over, winner
            }

            if (board.isFull()) {
                board.printBoard();
                isDraw = true;
                System.out.println("\n--- It's a draw! ---");
                endTime = System.currentTimeMillis();
                duration = endTime - startTime; // Calculate duration
                System.out.println("Game duration: " + formatDuration(duration)); // Print duration immediately
                return new GameResult(player1.getName(), player2.getName(), null, true, duration); // Game over, draw
            }

            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void undoMove() {
        if (boardHistory.size() > 1) { // Need at least two states (initial + current) to undo
            redoHistory.push(boardHistory.pop()); // Move current state to redo
            this.board = new Board(boardHistory.peek()); // Restore previous state
            switchPlayer(); // Switch back to the player whose move was undone
            System.out.println("Undo successful.");
        } else {
            System.out.println("Cannot undo further.");
        }
    }

    private void redoMove() {
        if (!redoHistory.isEmpty()) {
            boardHistory.push(redoHistory.pop()); // Move state from redo to history
            this.board = new Board(boardHistory.peek()); // Restore redone state
            switchPlayer(); // Switch back to the player whose move was redone
            System.out.println("Redo successful.");
        } else {
            System.out.println("No moves to redo.");
        }
    }

    // Helper method to format duration
    private String formatDuration(long durationMillis) {
        long seconds = durationMillis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%d minutes and %d seconds", minutes, seconds);
    }
}
