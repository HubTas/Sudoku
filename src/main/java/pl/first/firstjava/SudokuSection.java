package pl.first.firstjava;

import java.util.List;

public abstract class SudokuSection {

    private List<SudokuField> fields;

    public SudokuSection(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
