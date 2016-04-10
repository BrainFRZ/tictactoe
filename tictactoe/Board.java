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
        if (column < 1 || column > 3 || row < 1 || row > 3) {
            return false;
        }

        if (xTurn) {
            squares[column - 1][row - 1] = State.X;
        } else {
            squares[column - 1][row - 1] = State.O;
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
        return squares[column-1][row-1];
    }

    public State verifyWinner() {
        State winner = null;

        //Test columns
        for (int c = 0; winner == null && c < 3; c++) {
            if (squares[0][c].equals(squares[1][c]) && squares[1][c].equals(squares[2][c])) {
                winner = squares[0][c];
            }
        }

        //Test rows
        for (int r = 0; winner == null && r < 3; r++) {
            if (squares[r][0].equals(squares[r][1]) && squares[r][1].equals(squares[r][2])) {
                winner = squares[r][0];
            }
        }

        //Test diagonals
        if (winner == null && squares[0][0].equals(squares[1][1])
                && squares[1][1].equals(squares[2][2]))
        {
            winner = squares[0][0];
        }
        if (winner == null && squares[0][2].equals(squares[1][1])
                && squares[1][1].equals(squares[2][0]))
        {
            winner = squares[0][2];
        }

        if (winner == State.BLANK) {
            winner = null;
        }

        return winner;
    }
}
