package pl.first.firstjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBox extends SudokuSection implements Cloneable {

    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i,(SudokuField) getSudokuFieldList().get(i).clone());
        }
        SudokuBox sudokuBox = new SudokuBox(fields);
        return sudokuBox;
    }
}
