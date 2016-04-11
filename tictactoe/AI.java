package tictactoe;

import java.util.ArrayList;

public abstract class AI {
    public abstract int[] getMove(Board board, int turns);

    public static ArrayList<Integer> getEmptyCells(Board board) {
        ArrayList<Integer> emptyCells = new ArrayList<>(9);

        for (int i = 0; i < 9; i++) {
            if (board.getCell(i / 3, i % 3) == CellState.BLANK) {
                emptyCells.add(i);
            }
        }

        return emptyCells;
    }
}
