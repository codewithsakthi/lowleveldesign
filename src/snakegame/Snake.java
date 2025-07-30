package snakegame;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Snake {
    private Deque<int[]> bodySegments;
    private Set<String> bodyLookup; // For efficient collision checking
    private int[] head;

    public Snake(int startRow, int startCol) {
        bodySegments = new LinkedList<>();
        bodyLookup = new HashSet<>();
        head = new int[]{startRow, startCol};

        bodySegments.add(head.clone());
        bodyLookup.add(head[0] + "," + head[1]);
    }

    // Attempts to move the snake. Returns true if successful, false if game over.
    public boolean move(int dx, int dy, Board board) {
        int[] newHead = new int[]{head[0] + dx, head[1] + dy};

        // Check for collision with walls or self
        if (!board.isValid(newHead[0], newHead[1]) || bodyLookup.contains(newHead[0] + "," + newHead[1])) {
            return false; // Game Over
        }

        // Add new head
        bodySegments.addFirst(newHead);
        bodyLookup.add(newHead[0] + "," + newHead[1]);
        head = newHead;

        return true;
    }

    // Called when food is eaten, snake grows
    public void grow() {
        // Do nothing, as the tail is not removed
    }

    // Called when no food is eaten, snake moves normally (tail removed)
    public void shrink() {
        int[] tail = bodySegments.removeLast();
        bodyLookup.remove(tail[0] + "," + tail[1]);
    }

    // Checks if a given coordinate is part of the snake's body
    public boolean contains(int row, int col) {
        return bodyLookup.contains(row + "," + col);
    }

    public int[] getHead() {
        return head;
    }

    public Deque<int[]> getBodySegments() {
        return bodySegments;
    }
}