package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class Main extends Application {

    static ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) throws IOException {
        StageSetter.buildStage(stage,"/menu.fxml",bundle.getString("menuTitle"),bundle);
    }

    public static void main(String[] args) {
        logger.info(bundle.getString("startApp"));
        launch();
    }
}