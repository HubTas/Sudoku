package pl.first.firstjava;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;

    public void setEmpty() {
        isEmpty = true;
    }

    public void setNotEmpty() {
        isEmpty = false;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    private boolean isEmpty;

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().append(value, ((SudokuField) o).value).isEquals();
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
        return new ToStringBuilder(this).append("value", value).toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o == null || this == null) {
            throw new NullPointerException("Obj is empty");
        }
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
