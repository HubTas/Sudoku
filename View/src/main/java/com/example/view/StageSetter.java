package com.example.view;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageSetter {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        StageSetter.stage = stage;
    }

    private static Parent loadFxml(String fxml, ResourceBundle bundle) throws IOException {
        return new FXMLLoader(StageSetter.class.getResource(fxml),bundle).load();
    }

    public static void buildStage(String path, ResourceBundle bundle) throws IOException {
        stage.setScene(new Scene(loadFxml(path,bundle)));
        stage.sizeToScene();
        stage.show();
    }

    public static void buildStage(String path,String title, ResourceBundle bundle)
            throws IOException {
        stage.setScene(new Scene(loadFxml(path,bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();
    }

    public static void buildStage(Stage stage, String path,String title, ResourceBundle bundle)
            throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(path,bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();
    }
}
