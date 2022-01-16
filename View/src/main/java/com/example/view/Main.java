package com.example.view;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    static ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) throws IOException {
        StageSetter.buildStage(stage,"/menu.fxml",bundle.getString("menuTitle"),bundle);
    }

    public static void main(String[] args) throws IOException {
        FileHandler file = new FileHandler("Main log");
        logger.addHandler(file);
        logger.info(bundle.getString("startApp"));
        launch();
    }
}