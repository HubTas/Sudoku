package pl.first.firstjava;

public abstract class SudokuSection {
    private SudokuField[] container = new SudokuField[9];

    public SudokuSection() {
        for (int i = 0; i < 9; i++) {
            container[i] = new SudokuField();
        }
    }

    public void setValues(SudokuField[] values) {
        for (int i = 0; i < 9; i++) {
            container[i].setFieldValue(values[i].getFieldValue());
        }
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (container[j].getFieldValue() == container[i].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
