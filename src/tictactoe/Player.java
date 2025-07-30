package tictactoe;

public abstract class Player {
    protected String name;
    protected char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    // Abstract method for making a move, to be implemented by HumanPlayer and Bot
    public abstract int[] getMove(Board board);
}
