package com.example.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Cords {
    private int intx;
    private int inty;

    public Cords(int x, int y) {
        this.intx = x;
        this.inty = y;
    }

    public int getIntx() {
        return intx;
    }

    public int getInty() {
        return inty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", intx)
                .append("y", inty)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cords cords = (Cords) o;

        return new EqualsBuilder().append(intx, cords.intx).append(inty, cords.inty).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(intx).append(inty).toHashCode();
    }
}
