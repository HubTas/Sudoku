package pl.first.firstjava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class SudokuBoard implements Serializable, Cloneable, Prototype<SudokuBoard> {
    private SudokuSolver solver;
    private SudokuField[][] board = new SudokuField[9][9];

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().append(board, ((SudokuBoard) o).board).isEquals();
    }

    @Override
    public Prototype<SudokuBoard> clone() throws CloneNotSupportedException {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.setBoard(i, j, getBoard(i, j));
            }
        }

        return sudokuBoard;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(solver).append(board).toHashCode();
    }

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9;j++) {
                this.board[i][j] = new SudokuField(0);
            }
        }
    }

    public void setEditable(int x, int y) {
        board[x][y].setEmpty();
    }

    public void setNotEditable(int x, int y) {
        board[x][y].setNotEmpty();
    }

    public boolean isEditable(int x, int y) {
        return board[x][y].isEmpty();
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

    public int getBoard(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public List<SudokuField> getThisBoard() {
        List<SudokuField> fields = new ArrayList<SudokuField>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields.add(getField(i, j));
            }
        }

        return fields;
    }

    public SudokuField getField(int x, int y) {
        return board[x][y];
    }

    public void setBoard(int x, int y, int value) {
        board[x][y].setFieldValue(value);
        checkBoard();
    }

    private boolean checkBoard() {
        boolean flag = true;
        for (int i = 0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (!getBox(i, j).verify()) {
                    flag = false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                flag = false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!getColumn(i).verify()) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean isSudokuSafe() {
        return checkBoard();
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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    string.append(board[i][j].getFieldValue());
                }
            }
        return string.toString();
    }

    public SudokuBoard makeSudokuBoardFromString(String values) {
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                this.setBoard(i,j,Character.getNumericValue(values.charAt(i * 9 + j)));
            }
        }
        return this;
    }
}
