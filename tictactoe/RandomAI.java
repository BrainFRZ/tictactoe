package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class RandomAI implements AI {
    Random generator;

    public RandomAI() {
        generator = new Random();
    }

    @Override
    public int[] getMove(Board board) {
        ArrayList<Integer> emptyCells = new ArrayList<>(9);

        for (int i = 0; i < 9; i++) {
            if (board.getCell(i / 3, i % 3) == Board.State.BLANK) {
                emptyCells.add(i);
            }
        }

        int move = emptyCells.get(generator.nextInt(emptyCells.size()));
        int cell[] = { move / 3, move % 3};
        return cell;
    }
}
