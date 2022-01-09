package com.example.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class menuController {
    @FXML
    private ComboBox SudokuDifficulty;

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public static String getLevel() {
        return level;
    }

    private static String level;

    private InfoWindow window = new InfoWindow();

    private final Logger logger = Logger.getLogger(menuController.class.getName());

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
    void ChangeLangToEng(ActionEvent event) {
        try{
            Locale.setDefault(new Locale("eng"));
            StageSetter.buildStage("/menu.fxml",bundle.getString("menuTitle"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ChangeLangToPl(ActionEvent event) {
        try{
            Locale.setDefault(new Locale("pl"));
            StageSetter.buildStage("/menu.fxml",bundle.getString("menuTitle"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() throws IOException {
        SudokuDifficulty.getItems().setAll(
                bundle.getString("easyLvl"),
                bundle.getString("mediumLvl"),
                bundle.getString("hardLvl")
        );
    }

    @FXML
    void generateSudoku(ActionEvent event) throws IOException{
        try{
            if(level == null){
                menuController.level = SudokuDifficulty.getSelectionModel().getSelectedItem().toString();
            }
            StageSetter.buildStage("/board.fxml",bundle.getString("boardTitle"),bundle);
        } catch (NullPointerException e) {
            window.text(bundle.getString("error"),bundle.getString("noDiff"), Alert.AlertType.WARNING);
            logger.warning("Bad language choosen");
        }
    }

    @FXML
    void showAutorzy(ActionEvent event) {

    }

    @FXML
    void wychodzenie(ActionEvent event) {
        Platform.exit();
    }
}
