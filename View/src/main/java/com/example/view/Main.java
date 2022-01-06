package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;


public class Main extends Application {

    ResourceBundle bundle = ResourceBundle.getBundle("Language");
    @Override
    public void start(Stage stage) throws IOException {
        StageSetter.buildStage(stage,"/menu.fxml","Sudoku",bundle);
    }

    public static void main(String[] args) {
        launch();
    }
}