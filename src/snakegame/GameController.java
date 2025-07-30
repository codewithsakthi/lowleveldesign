package snakegame;

import java.util.Arrays;
import java.util.Scanner;

public class GameController {
    private Board board;
    private Snake snake;
    private Food food;
    private int score;
    private Scanner scanner;

    private static final int[][] directions = {
            {-1, 0},  // Up
            {1, 0},   // Down
            {0, -1},  // Left
            {0, 1}    // Right
    };

    public GameController(Board board, Snake snake, Food food) {
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.score = 1; // Start score at 1 as per original code
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        food.generate(board, snake); // Generate initial food
    }

    public void gameLoop() {
        boolean gameOver = false;

        while (!gameOver) {
            board.print(score, snake, food); // Pass snake and food to board for rendering
            System.out.println("Enter direction (W/A/S/D)");
            char directionChar = scanner.next().toUpperCase().charAt(0);
            int dx = 0, dy = 0;

            switch (directionChar) {
                case 'W':
                    dx = directions[0][0];
                    dy = directions[0][1];
                    break;
                case 'S':
                    dx = directions[1][0];
                    dy = directions[1][1];
                    break;
                case 'A':
                    dx = directions[2][0];
                    dy = directions[2][1];
                    break;
                case 'D':
                    dx = directions[3][0];
                    dy = directions[3][1];
                    break;
                default:
                    System.out.println("Invalid input. Use W/A/S/D.");
                    continue;
            }

            if (!snake.move(dx, dy, board)) {
                gameOver = true;
                System.out.println("Game Over!");
                continue; // Exit loop
            }

            // Check if food is eaten
            if (Arrays.equals(snake.getHead(), food.getPosition())) {
                score++;
                snake.grow();
                food.generate(board, snake);
            } else {
                snake.shrink(); // Snake shrinks if no food is eaten
            }
        }
        scanner.close(); // Close scanner when game ends
    }
}