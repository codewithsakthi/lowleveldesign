package snakegame;

import java.util.Scanner;

public class SnakeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter board height and width:");
        int height = scanner.nextInt();
        int width = scanner.nextInt();

        Board board = new Board(height, width);
        Snake snake = new Snake(0, 0); // Initial snake head position
        Food food = new Food();
        GameController game = new GameController(board, snake, food);

        game.startGame();
        game.gameLoop();
        scanner.close();
    }
}
