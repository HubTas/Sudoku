package pl.first.firstjava;

/**
 * Klasa główna z metodą main.
 *
 * @author Jakub Mielczarek | Huber Tasarz
 */
public class App {
    public static void main(final String[] args) {
        SudokuBoard test = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        test.solveGame();
        test.printout();
    }
}
