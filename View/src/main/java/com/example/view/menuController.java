package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class menuController {
    @FXML
    private ComboBox SudokuDifficulty;

    @FXML
    private ComboBox SudokuLanguage;

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public static String getLevel() {
        return level;
    }

    public static String getLang() {
        return lang;
    }

    private static String level;
    private static String lang;

    @FXML
    private void initialize() throws IOException{
        SudokuDifficulty.getItems().setAll(
                bundle.getString("easyLvl"),
                bundle.getString("mediumLvl"),
                bundle.getString("hardLvl")
        );
        SudokuLanguage.getItems().setAll(
                bundle.getString("pl"),
                bundle.getString("eng")
        );
    }

    @FXML
    void generateSudoku(ActionEvent event) throws IOException{
        try{
            if(level == null){
                menuController.level = SudokuDifficulty.getSelectionModel().getSelectedItem().toString();
            }
            StageSetter.buildStage("/board.fxml",bundle);
        } catch (NullPointerException e) {
            System.out.println("Blad sceny");
        }
    }

    @FXML
    void confirmLanguage(ActionEvent event) {
        try{
            lang = SudokuLanguage.getSelectionModel().getSelectedItem().toString();
            if(lang.equals(bundle.getString("pl"))){
                Locale.setDefault(new Locale("pl"));
            } else if(lang.equals(bundle.getString("eng"))){
                Locale.setDefault(new Locale("eng"));
            }
            StageSetter.buildStage("/menu.fxml","Glowne menu", bundle);
        } catch (NullPointerException | IOException e ){
            System.out.println("blad");
        }
    }

    @FXML
    void showAutorzy(ActionEvent event) {

    }

    @FXML
    void wychodzenie(ActionEvent event) {

    }




    @FXML
    private Button autorzy;

    @FXML
    private Button button;

    @FXML
    private Label label1;

    @FXML
    private Button wyjdz;
}
