package com.example.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private MainController mainController;

    @FXML
    private ComboBox<String> SudokuDifficulty;

    @FXML
    private Button autorzy;

    @FXML
    private Button button;

    @FXML
    private Label label1;

    @FXML
    private Button wyjdz;

    @FXML
    void generateSudoku(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("board.fxml"));
        GridPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainController.setScreen(pane);
    }

    @FXML
    void showAutorzy(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("autorzy.fxml"));
        GridPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AutorzyController autorzyController = loader.getController();
        autorzyController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    void wychodzenie(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Easy","Medium","Hard");
        SudokuDifficulty.setItems(list);
    }
}
