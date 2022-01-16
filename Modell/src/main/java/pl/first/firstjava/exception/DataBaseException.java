package pl.first.firstjava.exception;

import java.util.Locale;

public class DataBaseException extends SudokuDaoException {
    public DataBaseException(String messageKey) {
        super(messageKey);
    }

    public DataBaseException(String messageKey, Locale locale) {
        super(messageKey, locale);
    }
}
