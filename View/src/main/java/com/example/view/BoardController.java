package com.example.view;

import exception.FileIsNullException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;
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
    private final TextField[][] textFields = new TextField[9][9];
    private SudokuBoard edit;


    @FXML
    private void initialize() throws IOException {
        FileHandler file = new FileHandler("Game log");
        logger.addHandler(file);
        board.solveGame();
        level.level(board, MenuController.getLevel());
        fillGridPane();
        try {
            cleaner();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void fillGridPane() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField();
                field.setMinSize(50, 50);
                field.setOpacity(100);
                if (board.getBoard(i,j) != 0) {
                    field.setStyle("-fx-text-fill: rgba(75,128,0,0.74);-fx-alignment: center;");
                    field.setDisable(true);
                    field.setText(String.valueOf(board.getBoard(i,j)));
                } else {
                    field.setStyle("-fx-background-color: rgba(255,0,0,0.11);"
                            + "-fx-alignment: center");
                    field.setDisable(false);
                    field.setText("");
                }
                grid.add(field,i,j);
            }
        }
    }

    public void cleaner() throws CloneNotSupportedException {
        edit = (SudokuBoard) board.clone();
        int number = 0;
        String level = MenuController.getLevel();
        if (level.equals(bundle.getString("easyLvl"))) {
            number = 5;
        } else if (level.equals(bundle.getString("mediumLvl"))) {
            number = 10;
        } else {
            number = 15;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bind(i, j);
            }
        }
        while (number > 0) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
            if (board.getBoard(column, row) == 0) {
                TextField f = (TextField) getNodeFromGridPane(grid, column, row);
                    f.setDisable(false);
                    bind(row, column);
                    f.clear();

                    f.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(final ObservableValue<? extends String> observable,
                                            final String oldValue, final String newValue) {
                            if (!(newValue.matches("[1-9]|"))) {
                                f.clear();
                            }
                        }
                    });
                    number--;
            }
                }

            }
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void bind(int i, int j) {

        try {
            JavaBeanIntegerPropertyBuilder builder =
                    JavaBeanIntegerPropertyBuilder.create();

            JavaBeanIntegerProperty intProperty =
                    builder.bean(board.getThisBoard().get(i * 9 + j)).name("value")
                            .build();

            Bindings.bindBidirectional(textFields[i][j].textProperty(), intProperty,
                    new NumberStringConverter());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
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
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(bundle.getString("readingTitle"));
        dialog.setHeaderText(bundle.getString("reader"));
        dialog.setContentText(bundle.getString("name"));

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        try {
            board = (SudokuBoard) sudokuReader(result.get()).clone();
            grid.getChildren().clear();
            fillGridPane();
            logger.info(bundle.getString("readGood"));
        } catch (Exception e) {
            logger.info(bundle.getString("readError"));
        }
    }

    @FXML
    void saveBoardToBase(ActionEvent event) throws IOException {
        try {
            sudokuMaker();
            logger.info(bundle.getString("saveGood"));
        } catch (Exception e) {
            logger.info(bundle.getString("saveError"));
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

    void sudokuMaker() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(bundle.getString("savingTitle"));
        dialog.setHeaderText(bundle.getString("saver"));
        dialog.setContentText(bundle.getString("name"));
        Optional<String> result = dialog.showAndWait();
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao(result.get())) {
            databaseSudokuBoardDao = jdbcSudokuBoardDao;
            databaseSudokuBoardDao.write(board);
        } catch (Exception e) {
            logger.info(bundle.getString("saveError"));
        }
    }
}
