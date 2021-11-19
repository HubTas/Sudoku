package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static java.lang.Character.UNASSIGNED;
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
    public void testGetField() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        assertEquals(UNASSIGNED, board.getBoard(0, 0));
    }

    @Test
    public void testSetField() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.setBoard(0, 0, 8);
        assertEquals(8, board.getBoard(0, 0));
    }

    @Test
    public void testDifferentBoards() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        System.out.println("Different Boards");
        SudokuBoard instance = new SudokuBoard(solver);
        SudokuBoard instance2 = new SudokuBoard(solver2);
        instance.solveGame();
        instance2.solveGame();
        assertFalse(instance.equals(instance2));
        assertFalse(instance.getColumn(0).equals(instance2.getColumn(0)));
        assertFalse(instance.getRow(0).equals(instance2.getRow(0)));
        assertFalse(instance.getBox(2, 2).equals(instance2.getBox(2, 2)));
        }

    @Test
    public void toStringTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuField field = new SudokuField(1);
        board.solveGame();
        assertNotNull(board.toString());
        assertNotNull(field.toString());
    }

    @Test
    public void equalsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        SudokuBoard instance2 = new SudokuBoard(solver2);
        assertNotNull(instance.equals(instance2) && instance2.equals(instance));
    }

    @Test
    public void hashCodeTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        SudokuBoard instance2 = new SudokuBoard(solver2);
        assertNotEquals(instance.hashCode(), instance2.hashCode());
    }
}