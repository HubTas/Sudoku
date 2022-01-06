package com.example.view;

import pl.first.firstjava.SudokuBoard;

import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class Difficulity {
    public static final int levelMultiplayer = 5;
    public static final int[] levels = {1, 2, 3};
    private Random rand = new Random();
    private Set<Cords> cords = new HashSet<>();
    private ResourceBundle bundle = ResourceBundle.getBundle("language");

    private void fillSet(int pom) {
        for (int i = 0; i < pom; i++) {
            boolean flag = false;
            while (!flag) {
                int x = rand.nextInt(9);
                int y = rand.nextInt(9);
                flag = cords.add(new Cords(x, y));
            }
        }
    }

    public SudokuBoard level(SudokuBoard board, String level) {
//        if(!(level.equals(bundle.getString("easyLvl"))
//                ||level.equals(bundle.getString("mediumLvl"))
//                ||level.equals(bundle.getString("hardLvl")))){
//    }
        if (level.equals(bundle.getString("easyLvl"))) {
            fillSet(levelMultiplayer * levels[0]);
        } else if (level.equals(bundle.getString("mediumLvl"))) {
            fillSet(levelMultiplayer * levels[1]);
        } else if (level.equals(bundle.getString("hardLvl"))) {
            fillSet(levelMultiplayer * levels[2]);
        }
        for (Cords cord : cords) {
            board.setBoard(cord.getX(), cord.getY(), 0);
            board.setEditable(cord.getX(), cord.getY());
        }
        return board;
    }
}