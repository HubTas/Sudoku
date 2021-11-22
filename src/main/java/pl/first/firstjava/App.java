package pl.first.firstjava;

/**
 * Klasa główna z metodą main.
 *
 * @author Jakub Mielczarek | Huber Tasarz
 */
public class App {
    public static void main(final String[] args) {
            SudokuSolver solver = new BacktrackingSudokuSolver();
            SudokuBoard test = new SudokuBoard(solver);
            test.solveGame();
            System.out.println(test.toString());
            //test.printout();
    }
}
