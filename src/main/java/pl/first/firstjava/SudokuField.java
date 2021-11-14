package pl.first.firstjava;

    public class SudokuField {
    private int value;

        public SudokuField(int value) {
            this.value = value;
        }

        public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }
}
