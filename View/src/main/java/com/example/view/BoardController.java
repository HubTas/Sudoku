package com.example.view;

import exception.FileIsNullException;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import pl.first.firstjava.BacktrackingSudokuSolver;
import pl.first.firstjava.Dao;
import pl.first.firstjava.FileSudokuBoardDao;
import pl.first.firstjava.JdbcSudokuBoardDao;
import pl.first.firstjava.SudokuBoard;
import pl.first.firstjava.SudokuSolver;
import pl.first.firstjava.exception.SudokuDaoException;

public class BoardController {
    @FXML
    private String title;

    @FXML
    private GridPane grid;

    @FXML
    private Button checkSudoku;

    @FXML
    private Button exit;

    @FXML
    private Button read;

    @FXML
    private Button save;

    private Difficulity level = new Difficulity();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(solver);
    private InfoWindow window = new InfoWindow();
    private final Logger logger = Logger.getLogger(BoardController.class.getName());
    private File file;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private FileChooser fileChooser = new FileChooser();
    private static SudokuBoard sudokuBoard;
    private Dao<SudokuBoard> databaseSudokuBoardDao;

    @FXML
    private void initialize() throws IOException {
        FileHandler file = new FileHandler("Game log");
        logger.addHandler(file);
        board.solveGame();
        level.level(board, MenuController.getLevel());
        fillGridPane();
    }

    private void fillGridPane() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField();
                field.setMinSize(50, 50);
                field.setOpacity(100);
                if (!(board.getBoard(i,j) == 0 || board.isEditable(i,j))) {
                    field.setStyle("-fx-text-fill: rgba(75,128,0,0.74);-fx-alignment: center;");
                    field.setDisable(true);
                    field.setText(String.valueOf(board.getBoard(i,j)));
                } else {
                    field.setStyle("-fx-background-color: rgba(255,0,0,0.11);"
                            + "-fx-alignment: center");
                    field.setText("");
                }
                grid.add(field,j,i);
            }
        }
    }

    private boolean isValueValid() {
        boolean isValueValid = true;
        for (int i = 0; i < 81; i++) {
            String value = ((TextField) grid.getChildren().get(i)).getText();
            if (!(value.matches("[1-9]") || value.equals(""))) {
                isValueValid = false;
            }
        }
        return isValueValid;
    }

    private void update() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String value = ((TextField) grid.getChildren().get(i * 9 + j)).getText();
                if (!value.equals("")) {
                    board.setBoard(i,j,Integer.parseInt(value));
                } else {
                    board.setBoard(i,j,0);
                }
            }
        }
    }

    @FXML
    private void check(ActionEvent event) {

        if (!isValueValid()) {
            window.text(bundle.getString("error"),bundle.getString("wrongValue"),
                    Alert.AlertType.WARNING);
            logger.warning(bundle.getString("wrongValue"));
        } else {
            update();
            if (board.isSudokuSafe()) {
                window.text(bundle.getString("score"),bundle.getString("easyWin"),
                        Alert.AlertType.INFORMATION);
                logger.info(bundle.getString("easyWin"));
            } else {
                window.text(bundle.getString("score"),bundle.getString("loose"),
                        Alert.AlertType.INFORMATION);
                logger.info(bundle.getString("loose"));
            }
        }
    }

    @FXML
    void close(ActionEvent event) {
        logger.info(bundle.getString("closeApp"));
        Platform.exit();
    }

    @FXML
    private void saveToFile(ActionEvent event) throws SudokuDaoException {
        if (isValueValid()) {
            update();
            try {
                fileSudokuBoardDao = new FileSudokuBoardDao("plik.txt");
                fileSudokuBoardDao.write(board);
                logger.info(bundle.getString("save"));
            } catch (NullPointerException e) {
                throw new FileIsNullException(bundle.getString("fileNull"));
            } catch (SudokuDaoException e) {
                throw new SudokuDaoException(bundle.getString("saveFailed"));
            }
        } else {
            logger.warning(bundle.getString("wrongValue"));
            window.text(bundle.getString("error"), bundle.getString("wrongValue"),
                    Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void loadFromFile(ActionEvent event) throws SudokuDaoException {
        if (isValueValid()) {
            update();
        }
        try {
            fileSudokuBoardDao = new FileSudokuBoardDao("plik.txt");
            sudokuBoard = fileSudokuBoardDao.read();
            bundle.getString("read");
            logger.info(sudokuBoard.toString());
        } catch (NullPointerException e) {
            throw new FileIsNullException(bundle.getString("fileNull"));
        } catch (SudokuDaoException e) {
            throw new SudokuDaoException(bundle.getString("loadFailed"));
        }
        if (sudokuBoard != null) {
            board = sudokuBoard;
            grid.getChildren().clear();
            fillGridPane();
        }
    }

    @FXML
    void readBoardFromBase(ActionEvent event) {
        try {
            board = (SudokuBoard) sudokuReader("Tabela").clone();
            grid.getChildren().clear();
            fillGridPane();
        } catch (Exception e) {
            logger.info("Nie udalo sie odczytac");
        }
    }

    @FXML
    void saveBoardToBase(ActionEvent event) throws IOException {
        try {
            sudokuMaker("Tabela");
            logger.info("Udalo sie");
        } catch (Exception e) {
            logger.info("Nie udalo sie zapisac");
        }
    }

    SudokuBoard sudokuReader(String file) {
        SudokuBoard baseBoard = null;
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao(file)) {
            databaseSudokuBoardDao = jdbcSudokuBoardDao;
            baseBoard = databaseSudokuBoardDao.read();
        } catch (SudokuDaoException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseBoard;
    }

    void sudokuMaker(String file) {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao("Tabela")) {
            databaseSudokuBoardDao = jdbcSudokuBoardDao;
            databaseSudokuBoardDao.write(board);
        } catch (Exception e) {
            logger.info("Nie udalo sie zapisac");
        }
    }
}
