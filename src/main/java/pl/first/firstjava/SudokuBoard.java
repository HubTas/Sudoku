package pl.first.firstjava;

public class SudokuBoard {
    private BacktrackingSudokuSolver solver;
    private int[][] board;
    public SudokuBoard(BacktrackingSudokuSolver solver) {
        this.solver = solver;
        board = new int[9][9];
    }
    public void solveGame()
    {
        solver.solve(this);
    }

    boolean isSafe(int row, int col, int number) {
        for (int i = 0;i < 9;i++) {
            if (board[row][i] == number) {
                return false;
            }                        //Sprawdzanie czy jest juz taka liczba w wierszu
        }

        for (int i = 0;i < 9;i++) {
            if (board[i][col] == number) {
                return false;               //Sprawdzanie czy jest juz taka liczba w kolumnie
            }
        }

        int newRow = row - row % 3;
        int newCol = col - col % 3;
        for (int i = 0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (board[i + newRow][j + newCol] == number) {
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
                System.out.print("  " + board[i][j] + "  " + "│");
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
        return board[x][y];
    }
    public void setBoard(int x, int y, int value) {board[x][y] = value; }
}
