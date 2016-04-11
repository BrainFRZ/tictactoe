package tictactoe;

/**
 * Handles a smart AI. At this time, the AI always plays as O, although that will change in a
 * later version.
 */
public class HardAI extends AI {
    @Override
    public int[] getMove(Board board, int turns) {
        //Grab center square if it's empty
        if (board.getCell(1, 1) == CellState.BLANK) {
            return Board.intToCell(4);
        }

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

        //Grab a corner if one's available
        final int[] corners = { 0, 6, 2, 8 };
        int[] currentCell;
        for (int i = 0; i < 4; i++) {
            currentCell = Board.intToCell(corners[i]);
            if (board.getCell(currentCell) == CellState.BLANK) {
                return currentCell;
            }
        }

        //Grab a side if one's available {At this point, it must be, or there's a problem!}
        for (int i = 1; i < 8; i += 2) {
            currentCell = Board.intToCell(i);
            if (board.getCell(currentCell) == CellState.BLANK) {
                return currentCell;
            }
        }

        throw new IllegalStateException("No move is possible");
    }
}
