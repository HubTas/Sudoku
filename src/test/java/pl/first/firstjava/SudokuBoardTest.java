package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jakub Mielczarek | Huber Tasarz
 */

class SudokuBoardTest {

    public SudokuBoardTest(){
    }

    @Test
    public void testSudokuBoard() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard test = new SudokuBoard(solver);
        test.solveGame();
        SudokuBoard test2 = new SudokuBoard(solver);
        test2.solveGame();

        //test sprawdzajacy czy dwa sudoku sa rozne
        boolean flag = false;
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if(test.getBoard(i, j) != test2.getBoard(i, j)) {
                    flag = true;
                    break;
                }
            }
        }
        assertTrue(flag);

        //test sprawdzajacy czy liczby w wierszach kolumnach i kwadratach sie nie powtarzaja
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int z = 0; z < 9; z++) {
                    if(j != z)
                        assertNotEquals(test.getBoard(i, j), test.getBoard(i, z));
                    if(i != z)
                        assertNotEquals(test.getBoard(i, j), test.getBoard(z, j));
                }
                int newi = i - i % 3;
                int newj = j - j % 3;
                for(int x = newi; x < 3; x++) {
                    for(int y = newj; y < 3; y++) {
                        if(i != x && j != y)
                            assertNotEquals(test.getBoard(i, j), test.getBoard(x, y));
                    }
                }
            }
        }

        //test getera i setera
        test2.setBoard(3,5,7);
        assertEquals(test2.getBoard(3,5),7);

        //test issafe
        SudokuBoard test3 = new SudokuBoard(solver);

        test3.setBoard(0,0,1);
        test3.setBoard(0,2,2);
        test3.setBoard(6,1,3);

        assertFalse(test3.isSafe(0,1,1));
        assertFalse(test3.isSafe(0,1,2));
        assertFalse(test3.isSafe(0,1,3));
        assertTrue(test3.isSafe(4,4,4));

        //test solvegame
        SudokuBoard test4 = new SudokuBoard(solver);
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                assertEquals(test4.getBoard(i, j),0);
            }
        }
        test4.solveGame();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                assertTrue(test4.getBoard(i, j) > 0 && test4.getBoard(i, j) < 10);
            }
        }

        //test fillRemaining
//        SudokuBoard test5 = new SudokuBoard(solver);
//        BacktrackingSudokuSolver.roll3x3(0,0, test5);
//        BacktrackingSudokuSolver.roll3x3(3,3, test5);
//        BacktrackingSudokuSolver.roll3x3(6,6, test5);
//        boolean fillRemaining = BacktrackingSudokuSolver.fillRemaining(0,3,test5);
//        assertTrue(fillRemaining);

    }
}