package com.example.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private ComboBox sudokuDifficulty;

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public static String getLevel() {
        return level;
    }

    private static String level;

    private InfoWindow window = new InfoWindow();

    private final Logger logger = Logger.getLogger(MenuController.class.getName());

    @FXML
    private Button authors;

    @FXML
    private Button close;

    @FXML
    private Label diff;

    @FXML
    private Button engLang;

    @FXML
    private Button next;

    @FXML
    private Button plLang;

    @FXML
    void changeLangToEng(ActionEvent event) {
        try {
            Locale.setDefault(new Locale("eng"));
            StageSetter.buildStage("/menu.fxml",bundle.getString("menuTitle"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeLangToPl(ActionEvent event) {
        try {
            Locale.setDefault(new Locale("pl"));
            StageSetter.buildStage("/menu.fxml",bundle.getString("menuTitle"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() throws IOException {
        FileHandler file = new FileHandler("Menu log");
        logger.addHandler(file);
        sudokuDifficulty.getItems().setAll(
                bundle.getString("easyLvl"),
                bundle.getString("mediumLvl"),
                bundle.getString("hardLvl")
        );
    }

    @FXML
    void generateSudoku(ActionEvent event) throws IOException {
        try {
            if (level == null) {
                MenuController.level = sudokuDifficulty.getSelectionModel()
                        .getSelectedItem().toString();
                logger.info(bundle.getString("lvl") + level);
            }
            StageSetter.buildStage("/board.fxml",bundle.getString("boardTitle"),bundle);
        } catch (NullPointerException e) {
            window.text(bundle.getString("error"),bundle.getString("noDiff"),
                    Alert.AlertType.WARNING);
            logger.warning(bundle.getString("noDiff"));
        }
    }

    @FXML
    void showAutorzy(ActionEvent event) {
        ResourceBundle bundle1 = ResourceBundle.getBundle("com.example.view.Authors");
        window.text(bundle.getString("authors"), bundle1.getObject("Author1") + "\n"
                + bundle1.getObject("Author2") + "\n" + bundle1.getObject("University"),
                Alert.AlertType.INFORMATION);
    }

    @FXML
    void close(ActionEvent event) {
        logger.info(bundle.getString("closeApp"));
        Platform.exit();
    }
}
