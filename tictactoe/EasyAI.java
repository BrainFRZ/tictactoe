package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class EasyAI extends AI {
    Random generator;

    public EasyAI() {
        generator = new Random();
    }

    @Override
    public int[] getMove(Board board, int turns) {
        ArrayList<Integer> emptyCells = getEmptyCells(board);

        int move = emptyCells.get(generator.nextInt(emptyCells.size()));
        return Board.intToCell(move);
    }
}
