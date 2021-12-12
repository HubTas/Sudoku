package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("main.fxml"));
    StackPane stackPane = loader.load();
    Scene scene = new Scene(stackPane,600,600);
    stage.setScene(scene);
     stage.setTitle("Sudoku Game");
     stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}