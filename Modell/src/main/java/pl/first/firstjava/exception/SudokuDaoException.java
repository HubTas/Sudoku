package pl.first.firstjava.exception;

import java.io.IOException;
import java.util.Locale;

public class SudokuDaoException extends IOException {
    private final String messageKey;
    private final Locale locale;

    public SudokuDaoException(String messageKey) {
        this(messageKey, Locale.getDefault());
    }

    public SudokuDaoException(String messageKey, Locale locale) {
        this.messageKey = messageKey;
        this.locale = locale;
    }

    public String getLocalizedMessage() {
        return Messages.getMessageForLocale(messageKey, locale);
    }
}
