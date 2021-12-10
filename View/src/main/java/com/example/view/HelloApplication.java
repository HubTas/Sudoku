package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("board.fxml"));
    Pane pane = loader.load();
    Scene scene = new Scene(pane);

     stage.setTitle("Sudoku Game");
     stage.show();
     stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}