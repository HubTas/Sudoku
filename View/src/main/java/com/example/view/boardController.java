package com.example.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.first.firstjava.BacktrackingSudokuSolver;
import pl.first.firstjava.SudokuBoard;
import pl.first.firstjava.SudokuSolver;

import java.io.IOException;
import java.util.ResourceBundle;

public class boardController {

    @FXML
    private GridPane grid;

    @FXML
    private Button checkSudoku;

    @FXML
    private Button exit;

    private Difficulity level = new Difficulity();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(solver);
    private InfoWindow window = new InfoWindow();

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
                    field.setStyle("-fx-text-fill: rgba(75,128,0,0.74);-fx-alignment: center;");
                    field.setDisable(true);
                    field.setText(String.valueOf(board.getBoard(i,j)));
                } else {
                    field.setStyle("-fx-background-color: rgba(255,0,0,0.11);-fx-alignment: center");
                    field.setText("");
//
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
            window.text(bundle.getString("error"),bundle.getString("wrongValue"), Alert.AlertType.WARNING);
        } else {
            update();
            if(board.isSudokuSafe()){
                window.text(bundle.getString("score"),bundle.getString("easyWin"), Alert.AlertType.INFORMATION);
            } else {
                window.text(bundle.getString("score"),bundle.getString("loose"), Alert.AlertType.INFORMATION);
            }
        }
    }
    @FXML
    void close(ActionEvent event) {
        Platform.exit();
    }

}
