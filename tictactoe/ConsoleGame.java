package tictactoe;

import java.util.Scanner;

public class ConsoleGame {
    private final static Board board = new Board();
    private final static Scanner scanner = new Scanner(System.in);
    private final static int MAX_TURNS = 9;

    public static void main(String[] args) {
        int[] cell = { 0, 0 };

        Board.State winner = null;
        int turns;
        for (turns = 0; turns < MAX_TURNS && cell != null && winner == null; turns++) {
            drawBoard();
            cell = promptTurn(turns);
            if (cell == null) {
                System.out.println("Thanks for playing!");
            } else {
                board.makeTurn(cell[0], cell[1]);
                System.out.println();
                if (turns >= 3) {
                    winner = board.verifyWinner();
                }
            }
        }

        if (winner != null) {
            drawBoard();
            System.out.println(winner + " won!!");
        } else {
            System.out.println("It was a tie.");
        }
    }

    private static void drawBoard() {
        String b = "\n";
        b += "1  " + cellStr(1, 1) + " | " + cellStr(2, 1) + " | " + cellStr(3, 1) + "\n"
          +  "  ---+---+---\n"
          +  "2  " + cellStr(1, 2) + " | " + cellStr(2, 2) + " | " + cellStr(3, 2) + "\n"
          +  "  ---+---+---\n"
          +  "3  " + cellStr(1, 3) + " | " + cellStr(2, 3) + " | " + cellStr(3, 3) + "\n"
          +  "   1   2   3\n";

        System.out.println(b);
    }

    private static String cellStr(int column, int row) {
        return board.getCell(column, row).toString();
    }

    /**
     * Prompts the user for their turn.
     *
     * @param turn Turn number
     * @return Selected cell, or null if user wants to quit
     */
    private static int[] promptTurn(int turn) {
        if (turn % 2 == 0) {
            System.out.println("It's X's turn!");
        } else {
            System.out.println("It's O's turn!");
        }

        int[] cell = { 0, 0 };
        boolean validTurn = false;
        do {
            System.out.print("Enter your turn (<column> <row>): ");
            String[] input = scanner.nextLine().split(" ");

            if (input.length < 2) {
                if (input[0].equalsIgnoreCase("quit")) {
                    return null;
                } else {
                    System.out.println("Please enter both a column and a row!");
                }
            }
            else {
                try {
                    cell[0] = Integer.parseInt(input[0]);
                    cell[1] = Integer.parseInt(input[1]);

                    if (cell[0] < 1 || cell[0] > 3 || cell[1] < 1 || cell[1] > 3) {
                        System.out.println("That cell doesn't exist!");
                    } else if (board.getCell(cell[0], cell[1]) != Board.State.BLANK) {
                        System.out.println("That cell has already been played!");
                    } else {
                        validTurn = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid column and row.");
                }
            }
        } while (!validTurn);

        return cell;
    }
}
