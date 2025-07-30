package tictactoe;

public class Bot extends Player {

    public Bot(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int[] getMove(Board board) {
        System.out.println(name + "'s turn (" + symbol + ").");
        // Pass a COPY of the current game board to findBestMove.
        // This ensures the bot's internal calculations do not modify the actual game board.
        int[] bestMove = findBestMove(new Board(board));
        return bestMove;
    }

    private int[] findBestMove(Board currentBoard) {
        int bestVal = -1000;
        int[] move = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentBoard.getCell(i, j) == Board.EMPTY) {
                    Board nextBoardState = new Board(currentBoard);
                    nextBoardState.makeMove(i, j, symbol);
                    int moveVal = minimax(nextBoardState, 0, false);
                    if (moveVal > bestVal) {
                        bestVal = moveVal;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }


    private int minimax(Board currentBoard, int depth, boolean isMaximizingPlayer) {
        char winner = currentBoard.checkWin();
        if (winner == symbol) {
            return 10 - depth;
        }
        if (winner != Board.EMPTY) {
            return depth - 10;
        }
        if (currentBoard.isFull()) {
            return 0;
        }

        if (isMaximizingPlayer) { // Bot's turn (trying to maximize its score)
            int best = -1000; // Initialize with a very low value
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard.getCell(i, j) == Board.EMPTY) {
                        // Create a NEW board copy for this hypothetical move.
                        Board nextBoardState = new Board(currentBoard);
                        nextBoardState.makeMove(i, j, symbol); // Make bot's move

                        // Recurse for the opponent (minimizing player)
                        best = Math.max(best, minimax(nextBoardState, depth + 1, !isMaximizingPlayer));
                        // No explicit 'undo' needed
                    }
                }
            }
            return best;
        } else { // Opponent's turn (trying to minimize the bot's score)
            int best = 1000; // Initialize with a very high value
            char opponentSymbol = (symbol == 'X') ? 'O' : 'X'; // Determine opponent's symbol

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard.getCell(i, j) == Board.EMPTY) {
                        // Create a NEW board copy for this hypothetical move.
                        Board nextBoardState = new Board(currentBoard);
                        nextBoardState.makeMove(i, j, opponentSymbol); // Make opponent's move

                        // Recurse for the bot (maximizing player)
                        best = Math.min(best, minimax(nextBoardState, depth + 1, !isMaximizingPlayer));
                        // No explicit 'undo' needed
                    }
                }
            }
            return best;
        }
    }
}
