package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class HardAI extends AI {
    private static final Random generator = new Random();

    /**
     * Chooses the next move as intelligently as possible. If the center square is open, it will
     * choose it immediately. Thus, the center square will be filled on either turn 0 or 1. Next,
     * try to win, or else block the player from winning. If there are no immediate wins, try to
     * take a corner, or a side if a corner isn't available. This mode should always win or tie.
     *
     * @param board Board being used
     * @param turns Turn number isn't used in this implementation
     * @return { column, row } or null if no move available
     */
    @Override
    public int[] getMove(Board board, int turns) {
        if (board.getCell(1, 1) == CellState.BLANK) {
            return Board.intToCell(4);
        }

        if (turns >= 3) { //No one can win before 4th move
            final int X_WIN = 0, O_WIN = 1;
            int[] winner = predictWin(board);

            if (winner[O_WIN] != -1) {
                return Board.intToCell(winner[O_WIN]);
            } else if (winner[X_WIN] != -1) {
                return Board.intToCell(winner[X_WIN]);
            }
        }

        final int[] corners = { 0, 6, 2, 8 };
        final ArrayList<Integer> emptyCorners = getEmptyCells(board, corners);
        if (!emptyCorners.isEmpty()) {
            return Board.intToCell(emptyCorners.get(generator.nextInt(emptyCorners.size())));
        }

        final int[] sides = { 1, 3, 5, 7 };
        final ArrayList<Integer> emptySides = getEmptyCells(board, sides);
        if (!emptySides.isEmpty()) {
            return Board.intToCell(emptySides.get(generator.nextInt(emptySides.size())));
        }

        return null;
    }

    private static ArrayList<Integer> getEmptyCells(Board board, int[] cells) {
        ArrayList<Integer> list = new ArrayList<>(4);

        for (int i = 0; i < cells.length; i++) {
            if (board.getCell(i / 3, i % 3) == CellState.BLANK) {
                list.add(i);
            }
        }

        return list;
    }
}
