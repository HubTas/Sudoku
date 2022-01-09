package com.example.view;

import exception.FileIsNullException;
import exception.SudokuDaoException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import pl.first.firstjava.BacktrackingSudokuSolver;
import pl.first.firstjava.SudokuBoard;
import pl.first.firstjava.SudokuSolver;
import pl.first.firstjava.Dao;
import pl.first.firstjava.FileSudokuBoardDao;
import java.io.IOException;
import java.util.ResourceBundle;
import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static com.example.view.StageSetter.getStage;

public class boardController {

    @FXML
    private GridPane grid;

    @FXML
    private Button checkSudoku;

    @FXML
    private Button Exit;

    @FXML
    private Button Read;

    @FXML
    private Button Save;

    private Difficulity level = new Difficulity();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(solver);
    private InfoWindow window = new InfoWindow();
    private final Logger logger = Logger.getLogger(boardController.class.getName());
    private File file;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private FileChooser fileChooser = new FileChooser();
    private static SudokuBoard sudokuBoard;

    @FXML
    private void initialize() throws IOException {
        FileHandler file = new FileHandler("Game log");
        logger.addHandler(file);
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
            logger.warning("Wrong value of grid");
        } else {
            update();
            if(board.isSudokuSafe()){
                window.text(bundle.getString("score"),bundle.getString("easyWin"), Alert.AlertType.INFORMATION);
                logger.info("Winner");
            } else {
                window.text(bundle.getString("score"),bundle.getString("loose"), Alert.AlertType.INFORMATION);
                logger.info("Looser");
            }
        }
    }
    @FXML
    void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void saveToFile(ActionEvent event) throws SudokuDaoException {
        if(isValueValid()) {
            update();
            try {
                fileSudokuBoardDao = new FileSudokuBoardDao("plik.txt");
                fileSudokuBoardDao.write(board);
                logger.info("Saved to file");
            } catch (NullPointerException e) {
                throw new FileIsNullException("nullFile");
            }
        } else {
            logger.warning("Input is not valid");
            window.text(bundle.getString("warning"), bundle.getString("warnText"), Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void loadFromFile(ActionEvent event) throws SudokuDaoException {
        if(isValueValid()) {
            update();
        }
        try {
            fileSudokuBoardDao = new FileSudokuBoardDao("plik.txt");
            sudokuBoard = fileSudokuBoardDao.read();
            logger.info(sudokuBoard.toString());
        } catch (NullPointerException e) {
            throw new FileIsNullException("nullFile");
        }
        if(sudokuBoard != null) {
            board = sudokuBoard;
            grid.getChildren().clear();
            fillGridPane();
        }
    }

}
