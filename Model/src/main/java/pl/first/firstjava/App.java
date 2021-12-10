package pl.first.firstjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Klasa główna z metodą main.
 *
 * @author Jakub Mielczarek | Huber Tasarz
 */
public class App{
    public static void main(final String[] args) {
            SudokuSolver solver = new BacktrackingSudokuSolver();
            SudokuBoard test = new SudokuBoard(solver);
            test.solveGame();
            System.out.println(test.toString());
    }


}
