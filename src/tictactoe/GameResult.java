package tictactoe;

public class GameResult {
    private String player1Name;
    private String player2Name;
    private String winnerName; // Null if it's a draw
    private boolean isDraw;
    private long durationMillis;

    public GameResult(String player1Name, String player2Name, String winnerName, boolean isDraw, long durationMillis) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.winnerName = winnerName;
        this.isDraw = isDraw;
        this.durationMillis = durationMillis;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    public String getFormattedDuration() {
        long seconds = durationMillis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%d minutes and %d seconds", minutes, seconds);
    }

    @Override
    public String toString() {
        String outcome;
        if (isDraw) {
            outcome = "Draw";
        } else {
            outcome = winnerName + " won";
        }
        return String.format("Game between %s and %s: %s. Duration: %s",
                player1Name, player2Name, outcome, getFormattedDuration());
    }
}
