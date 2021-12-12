package pl.first.firstjava;

import java.util.ArrayList;
import java.util.List;

public class SudokuBox extends SudokuSection implements Cloneable {

    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        List<SudokuField> boxField = new ArrayList<>(getSudokuFieldList());
        SudokuBox sudokuBox = new SudokuBox(boxField);

        return sudokuBox;
    }
}
