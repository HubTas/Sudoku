package pl.first.firstjava;

public class BacktrackingSudokuSolver  implements SudokuSolver {
    //generowanie losowe kwadratu
    private void roll3x3(int startx,int starty, final SudokuBoard board) {
        int x;
        for (int i = startx;i < startx + 3;i++) {
            for (int j = starty;j < starty + 3;j++) {
                do {
                    x = (int) (Math.random() * 9 + 1);
                } while (!board.isSafe(i,j,x));
                board.setBoard(i,j,x);
            }
        }
    }

    private boolean fillRemaining(int i, int j, final SudokuBoard board) {
        if (j >= 9 && i < 8) {
            i = i + 1;
            j = 0;
        }
        if (i >= 9 && j >= 9) {
            return true;
        }
        if (i < 3) {
            if (j < 3) {
                j = 3;
            }
        } else if (i < 6) {
            if (j == 3) {
                j = j + 3;
            }
        } else {
            if (j == 6) {
                i = i + 1;
                j = 0;
                if (i >= 9) {
                    return true;
                }
            }
        }

        for (int z = 1; z <= 9; z++) {
            if (board.isSafe(i, j, z)) {
                board.setBoard(i,j,z);
                if (fillRemaining(i, j + 1, board)) {
                    return true;
                }
                board.setBoard(i,j,0);
            }
        }
        return false;
    }

    @Override
    public void solve(final SudokuBoard board) {
        roll3x3(0,0, board);                       //Generuje 3 wypelnione kwadraty po skosie
        roll3x3(3,3, board);
        roll3x3(6,6, board);
        fillRemaining(0, 3, board);                    //Wypelnia reszte pol
    }
}
