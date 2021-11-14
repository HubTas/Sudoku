package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
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

        SudokuField[] container = new SudokuField[9];
        SudokuField testField = new SudokuField(0);
        for (int i = 0; i < 9; i++) {
            container[i] = new SudokuField(0);
        }
        for (int i = 0; i < 9; i++) {
            container[i].setFieldValue(i+1);
        }

        SudokuBoard test6 = new SudokuBoard(solver);
        //test getRow
        assertFalse(test6.getRow(3).verify());
        test6.setBoard(2,3,5);
        test6.setBoard(6,3,5);
        assertFalse(test6.getRow(3).verify());

        SudokuBoard test7 = new SudokuBoard(solver);
        //test getColumn
        assertFalse(test7.getColumn(6).verify());

        //test getBox
        test7.setBoard(0,0,5);
        test7.setBoard(0,1,5);
        assertFalse(test7.getBox(0,0).verify());

    }
}