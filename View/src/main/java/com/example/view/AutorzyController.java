package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AutorzyController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    private Label ht;

    @FXML
    private Label jm;

    @FXML
    private Button powrot;

    @FXML
    void htEntered(MouseEvent event) {
        ht.setText("nr. Indeksu - 236674");
    }

    @FXML
    void jmExited(MouseEvent event) {
        jm.setText("Jakub Mielczarek");
    }

    @FXML
    void htExited(MouseEvent event) {
        ht.setText("Hubert Tasarz");
    }

    @FXML
    void jmEntered(MouseEvent event) {
        jm.setText("nr. Indeksu - 236602");
    }

    @FXML
    void wroc(ActionEvent event) {
       mainController.loadMainScreen();
    }
}
