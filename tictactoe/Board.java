package tictactoe;

public class Board {
    protected static enum State {
        BLANK, X, O;

        @Override
        public String toString() {
            if (this.name().equals("BLANK")) {
                return " ";
            } else {
                return this.name();
            }
        }

        public boolean matches(State state2) {
            return (this == state2 && state2 != BLANK);
        }
    };

    private State[][] squares;  //[column][row]
    private boolean xTurn;


    public Board(boolean xFirst) {
        xTurn = xFirst;
        squares = new State[3][3];
        clearBoard();
    }

    public Board() {
        this(true);
    }


    public boolean makeTurn(int column, int row) {
        if (column < 0 || column > 2 || row < 0 || row > 2) {
            return false;
        }

        if (xTurn) {
            squares[column][row] = State.X;
        } else {
            squares[column][row] = State.O;
        }

        xTurn = !xTurn;

        return true;
    }

    final public void clearBoard() {
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                squares[c][r] = State.BLANK;
            }
        }
    }

    public State getCell(int column, int row) {
        return squares[column][row];
    }

    public State verifyWinner() {
        State winner = null;

        //Test columns
        for (int c = 0; winner == null && c < 3; c++) {
            if (lineMatches(squares[0][c], squares[1][c], squares[2][c])) {
                winner = squares[0][c];
            }
        }

        //Test rows
        for (int r = 0; winner == null && r < 3; r++) {
            if (lineMatches(squares[r][0], squares[r][1], squares[r][2])) {
                winner = squares[r][0];
            }
        }

        //Test diagonals
        if (winner == null && lineMatches(squares[0][0], squares[1][1], squares[2][2]))
        {
            winner = squares[0][0];
        }
        if (winner == null && lineMatches(squares[0][2], squares[1][1], squares[2][0]))
        {
            winner = squares[0][2];
        }

        return winner;
    }

    private static boolean lineMatches(State cell1, State cell2, State cell3) {
        return (cell1.matches(cell2) && cell2.matches(cell3));
    }
}
