package com.example.view;

import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"Author1", "Jakub Mielczarek"},
                {"Author2", "Hubert Tasarz"},
                {"University","Lodz University of Technology"}
        };
    }
}
