package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static java.lang.Character.UNASSIGNED;

import java.util.ArrayList;
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
        SudokuColumn column = new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuField field = new SudokuField(1);
        board.solveGame();
        assertNotNull(board.toString());
        assertNotNull(field.toString());
        assertNotNull(column.toString());

    }

    @Test
    public void equalsTest() {
        SudokuColumn column = new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        SudokuColumn column2 = new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
                SudokuColumn column3 = new SudokuColumn(Arrays.asList(
                new SudokuField(9),
                new SudokuField(8),
                new SudokuField(7),
                new SudokuField(6),
                new SudokuField(5),
                new SudokuField(4),
                new SudokuField(3),
                new SudokuField(2),
                new SudokuField(1)));
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        SudokuBoard instance2 = new SudokuBoard(solver);
        SudokuBoard instance3 = new SudokuBoard(solver2);
        instance3.solveGame();
        SudokuField field = new SudokuField(1);
        SudokuField field2 = new SudokuField(1);
        SudokuField field3 = new SudokuField(2);
        assertTrue(instance.equals(instance2) && instance2.equals(instance));
        assertFalse(instance2.equals(instance3) && instance3.equals(instance2));
        assertTrue(instance.equals(instance));
        assertTrue(column.equals(column2) && column2.equals(column));
        assertFalse(column2.equals(column3) && column3.equals(column2));
        assertTrue(column.equals(column));
        assertTrue(field.equals(field2) && field2.equals(field));
        assertFalse(field.equals(field3) && field3.equals(field));
        assertTrue(field.equals(field));
    }

    @Test
    public void hashCodeTest() {
        SudokuColumn column = new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        SudokuColumn column2 = new SudokuColumn(Arrays.asList(
                new SudokuField(9),
                new SudokuField(8),
                new SudokuField(7),
                new SudokuField(6),
                new SudokuField(5),
                new SudokuField(4),
                new SudokuField(3),
                new SudokuField(2),
                new SudokuField(1)));
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuSolver solver2 = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        SudokuBoard instance2 = new SudokuBoard(solver2);
        assertNotEquals(instance.hashCode(), instance2.hashCode());
        assertNotEquals(column.hashCode(), column2.hashCode());
    }

    @Test
    public void getSudokuFieldListTest() {
        SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
        new SudokuField(1),
        new SudokuField(2),
        new SudokuField(3),
        new SudokuField(4),
        new SudokuField(5),
        new SudokuField(6),
        new SudokuField(7),
        new SudokuField(8),
        new SudokuField(9)));

        SudokuField sudokuField = new SudokuField(3);
        List<SudokuField> fields = sudokuRow.getSudokuFieldList();
        fields.set(0, sudokuField);
    }

    @Test
    public void rowCloneTest() throws CloneNotSupportedException {
        SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        List<SudokuField> fields = sudokuRow.getSudokuFieldList();
        SudokuRow row1 = new SudokuRow(fields);
        SudokuRow row2 = (SudokuRow) row1.clone();
        assertTrue(sudokuRow.equals(row1) && sudokuRow.equals(row2));
    }

    @Test
    public void columnCloneTest() throws CloneNotSupportedException {
        SudokuColumn sudokuColumn = new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        List<SudokuField> fields = sudokuColumn.getSudokuFieldList();
        SudokuColumn column1 = new SudokuColumn(fields);
        SudokuColumn column2 = (SudokuColumn) column1.clone();
        assertTrue(sudokuColumn.equals(column1) && sudokuColumn.equals(column2));
    }

    @Test
    public void boxCloneTest() throws CloneNotSupportedException {
        SudokuBox sudokuBox = new SudokuBox(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        List<SudokuField> fields = sudokuBox.getSudokuFieldList();
        SudokuBox box1 = new SudokuBox(fields);
        SudokuBox box2 = (SudokuBox) box1.clone();
        assertTrue(sudokuBox.equals(box1) && sudokuBox.equals(box2));
    }
}