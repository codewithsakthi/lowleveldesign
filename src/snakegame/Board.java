package snakegame;

import java.util.Arrays;

public class Board {
    private char[][] grid;
    private int height;
    private int width;

    private static final char Empty = '.';
    private static final char Food = 'F';
    private static final char Snake = 'S';

    // ANSI escape codes for colors
    private static final String Reset = "\u001b[00m";
    private static final String Red = "\u001b[31m"; // For Snake
    private static final String Smoke = "\u001b[25m"; // For Food
    private static final String Neon = "\u001b[34m"; // For Empty

    public Board(int h, int w) {
        this.height = h;
        this.width = w;
        grid = new char[height][width];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = Empty;
            }
        }
    }

    public void placeElement(int row, int col, char element) {
        if (isValid(row, col)) {
            grid[row][col] = element;
        }
    }

    public char getElement(int row, int col) {
        if (isValid(row, col)) {
            return grid[row][col];
        }
        return Empty;
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void print(int score, Snake snake, Food food) {
        initialize();

        for (int[] segment : snake.getBodySegments()) {
            placeElement(segment[0], segment[1], Snake);
        }

        placeElement(food.getPosition()[0], food.getPosition()[1], Food);


        System.out.println("Current Score: " + score);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = grid[i][j];
                switch (c) {
                    case Food:
                        System.out.print(Smoke + c + " " + Reset);
                        break;
                    case Snake:
                        System.out.print(Red + c + " " + Reset);
                        break;
                    case Empty:
                        System.out.print(Neon + c + " " + Reset);
                        break;
                }
            }
            System.out.println();
        }
    }
}