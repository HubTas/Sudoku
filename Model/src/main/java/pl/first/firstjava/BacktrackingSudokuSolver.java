package pl.first.firstjava;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver  implements SudokuSolver, Serializable {
    //generowanie losowe kwadratu
    private void roll3x3(int startx,int starty, final SudokuBoard board) {
        int x;
        int z = 0;
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        for (int i = startx;i < startx + 3;i++) {
            for (int j = starty;j < starty + 3;j++) {
                x = intList.get(z);
                z++;
                board.setBoard(i,j,x);
            }
        }
    }

    private boolean fillRemaining(int i, int j, final SudokuBoard board) {
        if (j >= 9) {
            i = i + 1;
            j = 0;
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
