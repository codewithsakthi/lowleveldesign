package tictactoe;

public class Move {
    private int row;
    private int col;
    private char playerSymbol;

    public Move(int row, int col, char playerSymbol) {
        this.row = row;
        this.col = col;
        this.playerSymbol = playerSymbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }
}
