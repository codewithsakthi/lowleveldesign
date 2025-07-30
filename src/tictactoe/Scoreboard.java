package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class Scoreboard {
    private Map<String, Integer> winCounts;
    private List<GameResult> gameHistory;

    public Scoreboard() {
        winCounts = new HashMap<>();
        gameHistory = new ArrayList<>();
    }

    public void recordGameResult(GameResult result) {
        gameHistory.add(result);
        if (result.getWinnerName() != null) {
            winCounts.put(result.getWinnerName(), winCounts.getOrDefault(result.getWinnerName(), 0) + 1);
        }
    }

    public void displayScores() {
        System.out.println("\n--- Scoreboard ---");

        // Display Win Counts
        if (winCounts.isEmpty()) {
            System.out.println("No wins recorded yet.");
        } else {
            System.out.println("--- Win Counts ---");
            winCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " wins"));
        }

        System.out.println("\n--- Game History ---");
        if (gameHistory.isEmpty()) {
            System.out.println("No games played yet.");
        } else {
            System.out.println("Last Played Game:");
            GameResult lastGame = gameHistory.get(gameHistory.size() - 1);
            System.out.println(lastGame);
        }
        System.out.println("--------------------");
    }
}