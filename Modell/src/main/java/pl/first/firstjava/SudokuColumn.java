package pl.first.firstjava;

import java.util.Arrays;
import java.util.List;

public class SudokuColumn extends SudokuSection implements Cloneable {

    public SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i,(SudokuField) getSudokuFieldList().get(i).clone());
        }
        SudokuColumn sudokuColumn = new SudokuColumn(fields);
        return sudokuColumn;
    }
}
