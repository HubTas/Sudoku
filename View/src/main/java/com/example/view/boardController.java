package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pl.first.firstjava.BacktrackingSudokuSolver;
import pl.first.firstjava.SudokuBoard;
import pl.first.firstjava.SudokuSolver;

import java.util.ResourceBundle;

public class boardController {

    @FXML
    private GridPane grid;

    @FXML
    private Button checkSudoku;

    private Difficulity level = new Difficulity();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(solver);

    @FXML
    private void initialize() {
        board.solveGame();
        level.level(board,menuController.getLevel());
        fillGridPane();
    }

    private void fillGridPane(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++)
            {
                TextField field = new TextField();
                field.setMinSize(50, 50);
                field.setOpacity(100);
                if(!(board.getBoard(i,j) == 0 || board.isEditable(i,j))){
                    field.setDisable(true);
                    field.setText(String.valueOf(board.getBoard(i,j)));
                } else if(board.getBoard(i,j) != 0 && board.isEditable(i,j)){
                    field.setText(String.valueOf(board.getBoard(i,j)));
                }
                grid.add(field,j,i);
            }
        }
    }
    private boolean isValueValid(){
        boolean isValueValid = true;
        for(int i = 0; i < 81; i++){
            String value = ((TextField) grid.getChildren().get(i)).getText();
            if(!(value.matches("[1-9]")||value.equals(""))){
                isValueValid = false;
            }
        }
        return isValueValid;
    }

    private void update(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                String value = ((TextField) grid.getChildren().get(i*9+j)).getText();
                if(!value.equals("")){
                    board.setBoard(i,j,Integer.parseInt(value));
                } else {
                    board.setBoard(i,j,0);
                }
            }
        }
    }

    @FXML
    private void check(ActionEvent event) {

        if(!isValueValid()){
            System.out.println("zla wartosc");
        } else {
            update();
            if(board.isSudokuSafe()){
                System.out.println("gitem jestes, udalo sie");
            } else {
                System.out.println("gowno z Ciebie, nie umiesz nawet w sudoku");
            }
        }
    }

}
