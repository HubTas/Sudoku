package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

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

        SudokuBoard test5 = new SudokuBoard(solver);
        test5.solveGame();
        //test getColumn
        assertTrue(test5.getColumn(3).verify());
        test5.setBoard(2,3,5);
        test5.setBoard(6,3,5);
        assertFalse(test5.getColumn(3).verify());

        SudokuBoard test6 = new SudokuBoard(solver);
        test6.solveGame();
        //test getRow
        assertTrue(test6.getRow(6).verify());

        //test getBox
        assertTrue(test6.getBox(0,0).verify());
        test.setBoard(0,0,5);
        test6.setBoard(1,1,5);
        assertFalse(test6.getBox(0,0).verify());

        SudokuBoard test7 = new SudokuBoard(solver);
        test5.setBoard(0,0,4);
        test5.setBoard(1,0,1);
        test5.setBoard(2,0,5);
        test5.setBoard(3,0,2);
        test5.setBoard(4,0,3);
        test5.setBoard(5,0,6);
        test5.setBoard(6,0,7);
        test5.setBoard(7,0,9);
        test5.setBoard(8,0,8);
        test5.setBoard(0,1,8);
        test5.setBoard(1,1,2);
        test5.setBoard(2,1,9);
        test5.setBoard(3,1,1);
        test5.setBoard(4,1,4);
        test5.setBoard(5,1,7);
        test5.setBoard(6,1,5);
        test5.setBoard(7,1,3);
        test5.setBoard(8,1,6);
        test5.setBoard(0,2,6);
        test5.setBoard(1,2,3);
        test5.setBoard(2,2,7);
        test5.setBoard(3,2,8);
        test5.setBoard(4,2,5);
        test5.setBoard(5,2,9);
        test5.setBoard(6,2,1);
        test5.setBoard(7,2,4);
        test5.setBoard(8,2,2);
        test5.setBoard(0,3,2);
        test5.setBoard(1,3,5);
        test5.setBoard(2,3,4);
        test5.setBoard(3,3,6);
        test5.setBoard(4,3,1);
        test5.setBoard(5,3,3);
        test5.setBoard(6,3,8);
        test5.setBoard(7,3,7);
        test5.setBoard(8,3,9);
        test5.setBoard(0,4,7);
        test5.setBoard(1,4,9);
        test5.setBoard(2,4,1);
        test5.setBoard(3,4,4);
        test5.setBoard(4,4,8);
        test5.setBoard(5,4,2);
        test5.setBoard(6,4,6);
        test5.setBoard(7,4,5);
        test5.setBoard(8,4,3);
        test5.setBoard(0,5,3);
        test5.setBoard(1,5,6);
        test5.setBoard(2,5,8);
        test5.setBoard(3,5,7);
        test5.setBoard(4,5,9);
        test5.setBoard(5,5,5);
        test5.setBoard(6,5,4);
        test5.setBoard(7,5,2);
        test5.setBoard(8,5,1);
        test5.setBoard(0,6,1);
        test5.setBoard(1,6,8);
        test5.setBoard(2,6,3);
        test5.setBoard(3,6,5);
        test5.setBoard(4,6,2);
        test5.setBoard(5,6,4);
        test5.setBoard(6,6,9);
        test5.setBoard(7,6,6);
        test5.setBoard(8,6,7);
        test5.setBoard(0,7,9);
        test5.setBoard(1,7,4);
        test5.setBoard(2,7,6);
        test5.setBoard(3,7,3);
        test5.setBoard(4,7,7);
        test5.setBoard(5,7,1);
        test5.setBoard(6,7,2);
        test5.setBoard(7,7,8);
        test5.setBoard(8,7,5);
        test5.setBoard(0,8,5);
        test5.setBoard(1,8,7);
        test5.setBoard(2,8,2);
        test5.setBoard(3,8,9);
        test5.setBoard(4,8,6);
        test5.setBoard(5,8,8);
        test5.setBoard(6,8,3);
        test5.setBoard(7,8,1);
        test5.setBoard(8,8,4);
        assertTrue(test6.getRow(6).verify());

        test5.setBoard(6,8,5);
        test5.setBoard(8,7,3);
        assertTrue(test6.getBox(2,2).verify());
    }
}