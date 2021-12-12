package pl.first.firstjava;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuSection implements Cloneable {

    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        List<SudokuField> rowField = new ArrayList<>(getSudokuFieldList());
        SudokuRow sudokuRow = new SudokuRow(rowField);

        return sudokuRow;
    }
}
