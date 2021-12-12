package com.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import pl.first.firstjava.SudokuBoard;
import pl.first.firstjava.SudokuSolver;
import pl.first.firstjava.BacktrackingSudokuSolver;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class BoardController implements Initializable {

    @FXML
    private GridPane grid;

    private SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(solver);


    private void fillGrid(){
        for(int i = 0;i < 9;i++){
            for(int j = 0;j < 9;j++){

                TextField field = new TextField();
                field.setMinSize(50, 50);
                field.setFont(Font.font(18));
                field.setDisable(true);
                field.setText(String.valueOf(board.getBoard(i,j)));
                grid.add(field,i,j);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board.solveGame();
        fillGrid();
    }
}
