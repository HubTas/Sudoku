package pl.first.firstjava;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuSection implements Cloneable {

    public SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        List<SudokuField> columnField = new ArrayList<>(getSudokuFieldList());
        SudokuColumn sudokuColumn = new SudokuColumn(columnField);

        return sudokuColumn;
    }
}
