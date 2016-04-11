/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Terry
 */
enum CellState {
    BLANK, X, O;

    @Override
    public String toString() {
        if (this.name().equals("BLANK")) {
            return " ";
        } else {
            return this.name();
        }
    }

    public boolean matches(CellState state2) {
        return this == state2 && state2 != BLANK;
    }
}
