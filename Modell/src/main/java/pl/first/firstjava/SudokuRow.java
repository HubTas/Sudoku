package pl.first.firstjava;

import java.util.Arrays;
import java.util.List;

public class SudokuRow extends SudokuSection implements Cloneable {

    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i,(SudokuField) getSudokuFieldList().get(i).clone());
        }
        SudokuRow sudokuRow = new SudokuRow(fields);
        return sudokuRow;
    }
}
