package snakegame;

import java.util.Random;

public class Food {
    private int[] position;

    public Food() {
        position = new int[2];
    }

    public void generate(Board board, Snake snake) {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(board.getHeight());
            int col = random.nextInt(board.getWidth());
            if (!snake.contains(row, col)) {
                position[0] = row;
                position[1] = col;
                break;
            }
        }
    }

    public int[] getPosition() {
        return position;
    }
}