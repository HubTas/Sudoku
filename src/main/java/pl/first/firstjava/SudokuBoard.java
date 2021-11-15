package pl.first.firstjava;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private SudokuSolver solver;
    private SudokuField[][] board = new SudokuField[9][9];

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9;j++) {
                this.board[i][j] = new SudokuField(0);
            }
        }
    }

    public void solveGame() {
        solver.solve(this);
    }

    boolean isSafe(int row, int col, int number) {
        for (int i = 0;i < 9;i++) {
            if (board[row][i].getFieldValue() == number) {
                return false;
            }                        //Sprawdzanie czy jest juz taka liczba w wierszu
        }

        for (int i = 0;i < 9;i++) {
            if (board[i][col].getFieldValue() == number) {
                return false;               //Sprawdzanie czy jest juz taka liczba w kolumnie
            }
        }

        int newRow = row - row % 3;
        int newCol = col - col % 3;
        for (int i = 0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (board[i + newRow][j + newCol].getFieldValue() == number) {
                    return false;                //Sprawdzenie czy jest juz taka liczba w kwadracie
                }
            }
        }
        return true;
    }

    public void printout() {
        System.out.println("┌—————┬—————┬—————┬—————┬—————┬—————┬—————┬—————┬—————┐");
        for (int i = 0;i < 9;i++) {
            System.out.print("│");
            for (int j = 0;j < 9;j++) {
                System.out.print("  " + board[i][j].getFieldValue() + "  " + "│");
            }
            System.out.println();
            if (i < 8) {
                System.out.println("├—————┼—————┼—————┼—————┼—————┼—————┼—————┼—————┼—————┤");
            } else {
                System.out.println("└—————┴—————┴—————┴—————┴—————┴—————┴—————┴—————┴—————┘");
            }
        }
    }

    public int getBoard(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void setBoard(int x, int y, int value) {
        board[x][y].setFieldValue(value);
        checkBoard();
    }

    private boolean checkBoard() {
        for (int i = 0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }
        return true;
    }

    public SudokuRow getRow(int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0;i < 9; i++) {
            fields.set(i, board[y][i]);
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, board[i][x]);
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields.set(index++, board[x * 3 + i][y * 3 + j]);
            }
        }
        return new SudokuBox(fields);
    }

}
