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
        final int[] corners = { 0, 2, 6, 8 };
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

        return null;
    }

    private static int[] predictWin(Board board) {
        int[] winner;
        int[] cells = new int[3];
        int xWin = -1, oWin = -1;

        //Test columns
        for (int c = 0; (xWin == -1 || oWin == -1) && c < 3; c++ ) {
            for (int r = 0; r < 3; r++) {
                cells[r] = c * 3 + r;
            }

            winner = winsNextTurn(board, cells[0], cells[1], cells[2]);
            if (winner != null) {
                if (winner[0] == CellState.X.ordinal()) {
                    xWin = winner[1];
                } else if (winner[0] == CellState.O.ordinal()) {
                    oWin = winner[1];
                }
            }
        }

        //Test rows
        for (int r = 0; (xWin == -1 || oWin == -1) && r < 3; r++) {
            for (int c = 0; c < 3; c++ ) {
                cells[c] = c * 3 + r;
            }

            winner = winsNextTurn(board, cells[0], cells[1], cells[2]);
            if (winner != null) {
                if (winner[0] == CellState.X.ordinal()) {
                    xWin = winner[1];
                } else if (winner[0] == CellState.O.ordinal()) {
                    oWin = winner[1];
                }
            }
        }

        //Test diagonals
        if (xWin == -1 || oWin == -1) {
            winner = winsNextTurn(board, 0, 4, 8);

            if (winner != null) {
                if (winner[0] == CellState.X.ordinal()) {
                    xWin = winner[1];
                }
                else if (winner[0] == CellState.O.ordinal()) {
                    oWin = winner[1];
                }
            }
        }
        if (xWin == -1 || oWin == -1) {
            winner = winsNextTurn(board, 2, 4, 6);

            if (winner != null) {
                if (winner[0] == CellState.X.ordinal()) {
                    xWin = winner[1];
                }
                else if (winner[0] == CellState.O.ordinal()) {
                    oWin = winner[1];
                }
            }
        }

        int[] winningMoves = { xWin, oWin };
        return winningMoves;
    }

    /**
     * Returns the cell location of the winning cell and which state would win it as an ordinal.
     * If there is no winner, null is returned. All cell locations must be a two-element array
     * with the column then row.
     *
     * @param board Tic-Tac-Toe Board being played on
     * @param cell1 Location
     * @param cell2
     * @param cell3
     * @return 3-element array: [CellState winner's ordinal][column][row]
     */
    private static int[] winsNextTurn(Board board, int cell1, int cell2, int cell3) {
        final int PLAYER = 0, CELL = 1;
        int[] winner = new int[2];

        CellState cs1 = board.getCell(Board.intToCell(cell1));
        CellState cs2 = board.getCell(Board.intToCell(cell2));
        CellState cs3 = board.getCell(Board.intToCell(cell3));

        if (cs1.matches(cs2) && cs3 == CellState.BLANK) {
            winner[PLAYER] = cs1.ordinal();
            winner[CELL]   = cell3;
            return winner;
        }

        if (cs2.matches(cs3) && cs1 == CellState.BLANK) {
            winner[PLAYER] = cs2.ordinal();
            winner[CELL]   = cell1;
            return winner;
        }

        if (cs1.matches(cs3) && cs2 == CellState.BLANK) {
            winner[PLAYER] = cs1.ordinal();
            winner[CELL]   = cell2;
            return winner;
        }

        return null;
    }
}
