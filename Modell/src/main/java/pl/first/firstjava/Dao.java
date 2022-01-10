package pl.first.firstjava;

import pl.first.firstjava.exception.SudokuDaoException;

public interface Dao<T> {
    T read() throws SudokuDaoException;

    void write(T obj) throws SudokuDaoException;
}
