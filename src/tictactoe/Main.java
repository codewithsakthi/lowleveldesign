package tictactoe;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Scoreboard scoreboard = new Scoreboard();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    playWithFriend();
                    break;
                case 2:
                    playWithBot();
                    break;
                case 3:
                    scoreboard.displayScores();
                    break;
                case 4:
                    System.out.println("Exiting Tic-Tac-Toe. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Tic-Tac-Toe Main Menu ---");
        System.out.println("1) Play with Friend");
        System.out.println("2) Play with Bot (Impossible to beat)");
        System.out.println("3) Scoreboard");
        System.out.println("4) Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print("Enter your choice: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static void playWithFriend() {
        System.out.print("Enter Player 1 Name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter Player 2 Name: ");
        String player2Name = scanner.nextLine();

        HumanPlayer player1 = new HumanPlayer(player1Name, 'X');
        HumanPlayer player2 = new HumanPlayer(player2Name, 'O');

        Game game = new Game(player1, player2);
        GameResult result = game.play();
        scoreboard.recordGameResult(result);
    }

    private static void playWithBot() {
        System.out.print("Enter Player Name: ");
        String playerName = scanner.nextLine();
        System.out.print("Enter Bot Name: ");
        String botName = scanner.nextLine();

        HumanPlayer player = new HumanPlayer(playerName, 'X');
        Bot bot = new Bot(botName, 'O');

        Game game = new Game(player, bot);
        GameResult result = game.play();
        scoreboard.recordGameResult(result);
    }
}