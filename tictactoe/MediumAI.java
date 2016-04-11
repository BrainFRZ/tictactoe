package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class MediumAI extends AI {
    private static Random generator = new Random();

    @Override
    public int[] getMove(Board board, int turns) {
        if (turns >= 3) {
            final int X_WIN = 0, O_WIN = 1;
            int[] winner = predictWin(board);

            //If there's an O-win (computer win), take that first, or else block X-win
            if (winner[O_WIN] != -1) {
                return Board.intToCell(winner[O_WIN]);
            } else if (winner[X_WIN] != -1) {
                return Board.intToCell(winner[X_WIN]);
            }
        }

        ArrayList<Integer> emptyCells = getEmptyCells(board);
        int move = emptyCells.get(generator.nextInt(emptyCells.size()));
        return Board.intToCell(move);    }
}
