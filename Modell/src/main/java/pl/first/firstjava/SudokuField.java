package pl.first.firstjava;

//import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField>, Observer {
    private int value;

    private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

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

    @Override
    public void registerChangeListener(PropertyChangeListener newListener) {
        if (!listener.contains(newListener)) {
            listener.add(newListener);
        }
    }

    @Override
    public void removeChangeListener(PropertyChangeListener newListener) {
        listener.remove(newListener);
    }

    //    private void notifyListeners(String property, int oldValue, int newValue) {
    //        for (PropertyChangeListener name : listener) {
    //            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    //        }
    //    }

    public List<PropertyChangeListener> getPropertyChangeListener() {
        return listener;
    }
}
