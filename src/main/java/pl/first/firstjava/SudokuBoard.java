package pl.first.firstjava;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private SudokuSolver solver;
    private SudokuField[][] board = new SudokuField[9][9];
    private List<SudokuRow>rows = Arrays.asList(new SudokuRow[9]);
    private List<SudokuBox>boxes = Arrays.asList(new SudokuBox[9]);
    private List<SudokuColumn>columns = Arrays.asList(new SudokuColumn[9]);
    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9;j++) {
                this.board[i][j] = new SudokuField();
            }
        }
        for (int i = 0; i < 9; i++){
            rows.set(i, new SudokuRow());
            columns.set(i, new SudokuColumn());
            boxes.set(i, new SudokuBox());
        }
        SudokuField[] tmp_tab = new SudokuField[9];
        SudokuColumn column = new SudokuColumn();
        SudokuRow row = new SudokuRow();
        SudokuBox box = new SudokuBox();
        for (int j = 0; j < 9; j++){
            for (int i = 0; i < 9; i++){
                tmp_tab[i] = board[i][j];
            }
            column.setValues(tmp_tab);
            columns.set(j, column);
        }
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                tmp_tab[j] = board[i][j];
            }
            row.setValues(tmp_tab);
            rows.set(i, row);
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
        for (int i = 0; i < 9; i++) {
            if (!(getRow(i).verify() && getColumn(i).verify())) {
                return false;
            }
        }
        for (int i = 0;i < 9; i++) {
            if (!getBox(i).verify()) {
                return false;
            }
        }
        return true;
    }

    public SudokuRow getRow(int x){
        return rows.get(x);
    }
    public SudokuColumn getColumn(int x) {
       return columns.get(x);
    }


    public SudokuBox getBox(int x) {
       return boxes.get(x);
    }
}
