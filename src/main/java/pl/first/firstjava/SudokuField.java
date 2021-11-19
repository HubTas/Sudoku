package pl.first.firstjava;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SudokuField {
    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    public SudokuField(int value) {
            this.value = value;
        }

        public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SudokuField{"
                + "value=" + value
                + '}';
    }
}
