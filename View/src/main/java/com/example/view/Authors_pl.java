package com.example.view;

import java.util.ListResourceBundle;

public class Authors_pl extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"Author1", "Jakub Mielczarek"},
                {"Author2", "Hubert Tasarz"},
                {"University","Politechnika Lodzka"}
        };
    }
}
