package pl.first.firstjava;

public class SudokuBoard {
    private SudokuSolver solver;
    private SudokuField[][] board = new SudokuField[9][9];

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9;j++) {
                this.board[i][j] = new SudokuField();
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
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify() && getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0;i < 9;i += 3) {
            for (int j = 0;j < 9;j += 3) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuRow getRow(int y) {
        SudokuRow row = new SudokuRow();
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0;i < 9; i++) {
            fields[i] = board[y][i];
        }
        row.setValues(fields);
        return row;
    }

    public SudokuColumn getColumn(int x) {
        SudokuColumn column = new SudokuColumn();
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0;i < 9; i++) {
            fields[i] = board[i][x];
        }
        column.setValues(fields);
        return column;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        SudokuField[] fields = new SudokuField[9];
        int z = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[z] = board[x + i][y + j];
                z++;
            }
        }
        box.setValues(fields);
        return box;
    }
}

