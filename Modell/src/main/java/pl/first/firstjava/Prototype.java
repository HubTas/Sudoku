package pl.first.firstjava;

public interface Prototype<T> {
    Prototype<T> clone() throws CloneNotSupportedException;
}
